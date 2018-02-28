var app = angular.module('groceryListApp', ["ngRoute"]);

// configure the router to use with tag ng-view
app.config(function($routeProvider){
	$routeProvider
	
		.when("/", {
			templateUrl: "views/groceryList.html",
			controller: "HomeController"
		})
		
		.when("/addItem", {
			templateUrl: "views/inputItem.html",
			controller: "GroceryListItemController"
		})
		
		.when("/addItem/edit/:id", {
			templateUrl: "views/inputItem.html",
			controller: "GroceryListItemController"
		})
				
		.otherwise({
			redirectTo: "/"
		})
});

// cria um service que contem a lista de itens 
app.service("GroceryService", function($http){
	var groceryService = {};
	
	// cria um array vazio de itens 
	groceryService.groceryItems = [];

	// a lista de items é montada através de uma chamada REST
	$http.get("http://localhost:8080/grocerylist-backend/rest/groceryItems")
		.success(function(data) {
			groceryService.groceryItems = data;
			
			for (var item in groceryService.groceryItems) {
				// passa um integer que representa a data e cria um objeto Date
				groceryService.groceryItems[item].date = new Date(groceryService.groceryItems[item].date);
			}
		})
		.error(function(data, status) {
			alert("Things went wrong!");
		});

	// função para buscar por id
	groceryService.findById = function(id) {
		$http.get("http://localhost:8080/grocerylist-backend/rest/groceryItems/" + id)
		.success(function(entry) {
			return groceryService.groceryItems[entry];
		})
	};
	
	// atribui um id para um novo item
	groceryService.getNewId = function() {
		if (groceryService.newId) {
			groceryService.newId++;
			return groceryService.newId;
		} else {
			var maxId = _.max(groceryService.groceryItems, function(entry) { 
				return entry.id; 
			});
			groceryService.newId = maxId.id + 1;
			return groceryService.newId;
		}
	};
	
	// função para salvar/atualizar um item
	groceryService.save = function(entry) {
		var updatedItem = groceryService.findById(entry.id);

		if (updatedItem) {
			updatedItem.completed = entry.completed;
			updatedItem.itemName = entry.itemName;
			updatedItem.date = entry.date;
		} else {			
			$http.post("http://localhost:8080/grocerylist-backend/rest/groceryItems/add", entry)
			groceryService.groceryItems.push(entry);
		}
	};
	
	// função para remover um item
	groceryService.removeItem = function(entry) {
		var index = groceryService.groceryItems.indexOf(entry);
		groceryService.groceryItems.splice(index, 1);
	};

	// função para marcar e/ou desmarcar os itens
	groceryService.markCompleted = function(entry) {
		entry.completed = !entry.completed;
	};

	return groceryService;
});

// creates a controller that is intented to control the main page 
app.controller('HomeController', ['$scope', 'GroceryService', function($scope, GroceryService){
	
	$scope.groceryItems = GroceryService.groceryItems;

	$scope.removeItem = function(entry) {
		console.log("id:" + entry.id + ", itemName: " + entry.itemName);
		GroceryService.removeItem(entry);
	};
	
	$scope.markCompleted = function(entry) {
		GroceryService.markCompleted(entry);
	};

	$scope.$watch(
		function() { return GroceryService.groceryItems; },
		function(groceryItems) { $scope.groceryItems = GroceryService.groceryItems; }
	);

}]);

// creates a controller that is intented to control the items list 
app.controller('GroceryListItemController', ['$scope', '$routeParams', '$location', 'GroceryService', function($scope, $routeParams, $location, GroceryService){
	
	if (!$routeParams.id) {
		$scope.groceryItem = {id: 0, completed: false, itemName: "", date: new Date()};
	} else {
		$scope.groceryItem = _.clone(GroceryService.findById(parseInt($routeParams.id)));
	}
	
	$scope.groceryItems = GroceryService.groceryItems;
	
	$scope.save = function() {
		GroceryService.save( $scope.groceryItem );
		$location.path("/");
	}
			
}]);

app.directive('tbGroceryItem', function() {
	return {
		restrict: "E",
		templateUrl: "views/groceryItem.html"
	}
});