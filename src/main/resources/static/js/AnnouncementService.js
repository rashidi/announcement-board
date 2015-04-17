'use strict';

angular.module('announcementBoardApp.AnnouncementService', [])
    .factory('Announcement', ['$resource', function($resource) {
        var path = "./announcements";

        return {
            getAll: function() {
                return $resource(path).get();
            },
            save: function(title, content) {
                return $resource(path).save({
                    title: title,
                    content: content
                });
            }
        };
    }]);
