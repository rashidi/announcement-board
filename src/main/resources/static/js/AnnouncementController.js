'use strict';

var module = angular.module('announcementBoardApp.AnnouncementController', []);

module.controller('AnnouncementController', ['Announcement', '$scope', function(Announcement, $scope) {

    Announcement.getAll().$promise.then(function(data) {
        $scope.announcements = data.content;
    });

    $scope.save = function() {
        Announcement.save($scope.title, $scope.content);
    };

    var socket = new SockJS("/announcement-board/websocket/tracker");
    var stompClient = Stomp.over(socket);

    stompClient.connect({}, function(frame) {
        stompClient.subscribe('/topic/announcements', function(announcement) {
            showAnnouncement(announcement);
        });
    });

    function showAnnouncement(announcement) {
        $scope.announcements.push(JSON.parse(announcement.body));
        $scope.$apply();
    };
}]);
