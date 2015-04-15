'use strict';

var module = angular.module('announcementBoardApp.AnnouncementController',[]);

module.controller('TrackerController', ['$scope', function($scope) {
    
    $scope.announcements = [];
    var socket = new SockJS("/announcement-board/websocket/tracker");
    var stompClient = Stomp.over(socket);
    
    stompClient.connect({}, function(frame) {
        stompClient.subscribe('/topic/announcements', function(announcement) {
            showAnnouncement(announcement);
        });
    });
    
    function showAnnouncement(announcement) {
        console.log(announcement);
    };
}]);