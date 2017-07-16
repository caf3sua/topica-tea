(function() {
    'use strict';

    angular
        .module('topicaEventAmplifyApp')
        .controller('EventDeleteController',EventDeleteController);

    EventDeleteController.$inject = ['$uibModalInstance', 'entity', 'Event'];

    function EventDeleteController($uibModalInstance, entity, Event) {
        var vm = this;

        vm.event = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
//            Event.delete({id: id},
//                function () {
//                    $uibModalInstance.close(true);
//                });
        	Event.updateStatus({id : id, status: 'CANCEL', type: 0},
                    function () {
                        $uibModalInstance.close(true);
                    });
        }
    }
})();
