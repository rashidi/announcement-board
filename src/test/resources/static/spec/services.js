'use strict';

describe('service', function() {
    beforeEach(angular.mock.module('announcementBoardApp'));

    it('check the existence of RESTService', inject(function(RESTService) {
        expect(RESTService).toBeDefined();
    }));
});