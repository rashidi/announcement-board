module.exports = function(config){
  config.set({

    basePath : './',

    files : [
      'src/main/resources/static/bower_components/angular/angular.js',
      'src/main/resources/static/bower_components/angular-route/angular-route.js',
      'src/main/resources/static/bower_components/angular-mocks/angular-mocks.js',
      'src/main/resources/static/bower_components/angular-resource/angular-resource.js',
      'src/main/resources/static/js/**/*.js',
      'src/test/resources/static/spec/**/*.js'
    ],

    autoWatch : true,

    frameworks: ['jasmine'],

    browsers: ['PhantomJS'],

    plugins : [
            'karma-jasmine',
            'karma-phantomjs-launcher',
            'karma-junit-reporter'
            ],

    junitReporter : {
      outputFile: 'test_out/unit.xml',
      suite: 'unit'
    }

  });
};