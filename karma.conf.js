module.exports = function(config){
  config.set({

    basePath : './',

    files : [
      'src/main/resources/static/bower_components/angular/angular.js',
      'src/main/resources/static/bower_components/angular-route/angular-route.js',
      'src/main/resources/static/bower_components/angular-mocks/angular-mocks.js',
      'src/main/resources/static/components/**/*.js',
      'src/main/resources/static/view*/**/*.js'
    ],

    autoWatch : true,

    frameworks: ['jasmine'],

    browsers : ['Chrome'],

    plugins : [
            'karma-chrome-launcher',
            'karma-firefox-launcher',
            'karma-jasmine',
            'karma-junit-reporter'
            ],

    junitReporter : {
      outputFile: 'test_out/unit.xml',
      suite: 'unit'
    }

  });
};