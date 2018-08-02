hrApp.controller('EmployeeAddController', ['$scope', '$http', '$location', 'CommonResourcesFactory',
    function ($scope, $http, $location, CommonResourcesFactory) {
        $scope.employee = {};
        $scope.jobs = [];
        $scope.requiredErrorMessage = "Please fill out this form!";
        $scope.patternDateNotRespectedMessage = "The date format should be yyyy-mm-dd";
        $scope.patternCommisionNotRespectedMessage = "Commission should be in the format 0.XX";

        //TODO #HR1

        /**
         * Reset form
         */
        $scope.reset = function () {
            this.employee = {};
        };
        $http.get(CommonResourcesFactory.findAllDepartmentsUrl)
            .success(function (data, status, header, config) {
                $scope.departments = data;
            })
            .error(function (data, status, header, config) {
                alert('error:' + status);
            });
        $http.get(CommonResourcesFactory.findAllEmployeesUrl)
            .success(function(data, status, header, config){
                $scope.managers = data;
            })
            .error(function (data, status, header, config) {
                alert('error:' + status);
            });
        $http.get(CommonResourcesFactory.findAllJobsUrl)
            .success(function (data, status, header, config) {
                $scope.jobs = data;
            })
            .error(function (data, status, header, config) {
                alert('error:' + status);
            });

        /**
         * Persist an employee
         * @param addEmployee - employee to be persisted
         */
        $scope.create = function (addEmployee) {
            $http({url: $commonResourcesFactory.addEmployeeUrl, method: 'POST', data: addEmployee})
                .success(function (data) {
                    $scope.employee = data;
                    $location.url('/employeeView/' + $scope.employee.employeeId);
                });
        };

        $scope.datePattern = /^\d{4}-\d{2}-\d{2}$/;
        $scope.commissionPattern = /^[0]\.\d{1}(\d)?$/;
    }]);