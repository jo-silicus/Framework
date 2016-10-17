var app = angular.module('myApp', ['ngRoute','ngSanitize'])
    .config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider
                .when("/login", {
                    controller: "loginController",
                    templateUrl: "login.html"
                }).when("/home",
                {
                    controller: "homeController",
                    templateUrl: "app/home/views/home.html"
                }).
                otherwise({ redirectTo: "/login" });
        }])

angular.element(document).ready(function () {
    angular.bootstrap(document, ['myApp']);
});
