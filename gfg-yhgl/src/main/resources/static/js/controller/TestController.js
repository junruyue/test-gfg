var app=angular.module("myApp",[]);
app.controller("myCon",function ($scope,$http) {
    $http.get("/testFindUser").then(function (response) {

        $scope.hello=response.data;
    })
});