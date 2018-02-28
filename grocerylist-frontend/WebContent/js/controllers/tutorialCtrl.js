angular.module("tutorialCtrlModule", [])

.controller("TutorialCtrl", ["$scope", "Calculations", function($scope, Calculations){

		$scope.tutorialObject = {};
		$scope.tutorialObject.title = "Main Page";
		$scope.tutorialObject.subtitle = "Sub title";
		$scope.tutorialObject.bindOutput = 2;
		$scope.tutorialObject.pythagoreanOutput = 0;
		
		$scope.tutorialObject.firstname = "Thomas";
		$scope.tutorialObject.lastname = "Brown";
		
		$scope.timesTwo = function() {
			$scope.tutorialObject.bindOutput = Calculations.timesTwo($scope.tutorialObject.bindOutput);
		};

		$scope.pt = function() {
			$scope.tutorialObject.pythagoreanOutput = Calculations.pythagorean($scope.tutorialObject.bindOutput, 5);
		}
		
	}])
	
.controller("TutorialCtrl2", ["$scope", function($scope){
		$scope.secondTutorial = "This is the second tutorial.";
	}])

.factory("Calculations", function(){
		var calculations = {};
		
		calculations.timesTwo = function(a) {
			return a * 2;
		};
		
		calculations.pythagorean = function(a,b) {
			return (a*a) + (b*b);
		}
		
		return calculations;
	})

.directive("welcomeMessage", function(){
		return {
			restrict: "E",
			template: "<div>Howdy! How are you?</div>"
		}
	});