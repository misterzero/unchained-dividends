# unchained
This application was generated using JHipster 4.5.1, you can find documentation and help at [https://jhipster.github.io/documentation-archive/v4.5.1](https://jhipster.github.io/documentation-archive/v4.5.1).

## Development

Before configuring dependencies to build this project, you will need to set up an environment, byt following the below steps:

1. [TestRPC]: We need ot install a local ethereum network
    - Once installed, run the below command in a seperate terminal with default accounts:
    
        testrpc --account="0x259b452db2511c99382850b30ce76bc422baf5a0269cfad7ba3ad014bec801c1, 10000000000000000000" --account="0xbb97e2df4ca96b843d10779669839861bd442940f8c9ce46c2854f9364aeb304, 10000000000000000000" --account="0x4a4d567ae46123e06cc0f7ef7576c0db681702e98875e78274a5c86702b96818, 10000000000000000000" --account="0x4b13837e8640c99661d2402d87d37dd6a821cfa36c6720234a21fc2a79626f98, 10000000000000000000" --account="0xbe0866a3c4bb0a282fe67c9543a63edd12d04ca53df5f28ebc190cf10dfa864f, 10000000000000000000" --account="0x553a04d0cdcbb77c18b0e7ef871127b72b9f7c142e3c46cf34b74f2e7a0ec112, 10000000000000000000" --account="0xa4f829e31754082e5f6a2209a4cdae33783ba93fc887653672ed83d46e18a09b, 10000000000000000000" --account="0x5c6e0c9d7002ccee337a00968c33b8d544d433890e646fef8a8ac1df272a8fdf, 10000000000000000000" --account="0x37043c493d4e3fc1e57d1b79237a457980c92012bdea973c74fd363d98f8f00d, 10000000000000000000" --account="0x0f3853d8a00ac4482d76b77114b72b6b3fa3ccb7877414be86b4b0e384f24f25, 10000000000000000000" 
        
Before you can build this project, you must install and configure the following dependencies on your machine:

1. [Node.js][]: We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.
2. [Yarn][]: We use Yarn to manage Node dependencies.
   Depending on your system, you can install Yarn either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in [package.json](package.json).

    yarn install

We use yarn scripts and [Webpack][] as our build system.


Run the following commands in two separate terminals to create a blissful development experience where your browser
auto-refreshes when files change on your hard drive.

    ./mvnw
    yarn start

[Yarn][] is also used to manage CSS and JavaScript dependencies used in this application. You can upgrade dependencies by
specifying a newer version in [package.json](package.json). You can also run `yarn update` and `yarn install` to manage dependencies.
Add the `help` flag on any command to see how you can use it. For example, `yarn help update`.

The `yarn run` command will list all of the scripts available to run for this project.

### Managing dependencies

For example, to add [Leaflet][] library as a runtime dependency of your application, you would run following command:

    yarn add --exact leaflet

To benefit from TypeScript type definitions from [DefinitelyTyped][] repository in development, you would run following command:

    yarn add --dev --exact @types/leaflet

Then you would import the JS and CSS files specified in library's installation instructions so that [Webpack][] knows about them:

Edit [src/main/webapp/app/vendor.ts](src/main/webapp/app/vendor.ts) file:
~~~
import 'leaflet/dist/leaflet.js';
~~~

Edit [src/main/webapp/content/css/vendor.css](src/main/webapp/content/css/vendor.css) file:
~~~
@import '~leaflet/dist/leaflet.css';
~~~

Note: there are still few other things remaining to do for Leaflet that we won't detail here.

For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].

### Using angular-cli

You can also use [Angular CLI][] to generate some custom client code.

For example, the following command:

    ng generate component my-component

will generate few files:

    create src/main/webapp/app/my-component/my-component.component.html
    create src/main/webapp/app/my-component/my-component.component.ts
    update src/main/webapp/app/app.module.ts

## Building for production

To optimize the unchained application for production, run:

    ./mvnw -Pprod clean package

This will concatenate and minify the client CSS and JavaScript files. It will also modify `index.html` so it references these new files.
To ensure everything worked, run:

    java -jar target/*.war

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

Refer to [Using JHipster in production][] for more details.

## Testing

To launch your application's tests, run:

    ./mvnw clean test

### Client tests

Unit tests are run by [Karma][] and written with [Jasmine][]. They're located in [src/test/javascript/](src/test/javascript/) and can be run with:

    yarn test



For more information, refer to the [Running tests page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.
For example, to start a mariadb database in a docker container, run:

    docker-compose -f src/main/docker/mariadb.yml up -d

To stop it and remove the container, run:

    docker-compose -f src/main/docker/mariadb.yml down

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

    ./mvnw package -Pprod docker:build

Then run:

    docker-compose -f src/main/docker/app.yml up -d

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`jhipster docker-compose`), which is able to generate docker configurations for one or several JHipster applications.

## Continuous Integration (optional)

To configure CI for your project, run the ci-cd sub-generator (`jhipster ci-cd`), this will let you generate configuration files for a number of Continuous Integration systems. Consult the [Setting up Continuous Integration][] page for more information.

[JHipster Homepage and latest documentation]: https://jhipster.github.io
[JHipster 4.5.1 archive]: https://jhipster.github.io/documentation-archive/v4.5.1

[Using JHipster in development]: https://jhipster.github.io/documentation-archive/v4.5.1/development/
[Using Docker and Docker-Compose]: https://jhipster.github.io/documentation-archive/v4.5.1/docker-compose
[Using JHipster in production]: https://jhipster.github.io/documentation-archive/v4.5.1/production/
[Running tests page]: https://jhipster.github.io/documentation-archive/v4.5.1/running-tests/
[Setting up Continuous Integration]: https://jhipster.github.io/documentation-archive/v4.5.1/setting-up-ci/


[Node.js]: https://nodejs.org/
[Yarn]: https://yarnpkg.org/
[Webpack]: https://webpack.github.io/
[Angular CLI]: https://cli.angular.io/
[BrowserSync]: http://www.browsersync.io/
[Karma]: http://karma-runner.github.io/
[Jasmine]: http://jasmine.github.io/2.0/introduction.html
[Protractor]: https://angular.github.io/protractor/
[Leaflet]: http://leafletjs.com/
[DefinitelyTyped]: http://definitelytyped.org/
[TestRPC]: https://github.com/ethereumjs/testrpc 
[Truffle]: https://github.com/trufflesuite/truffle
[Solidity browser]: https://ethereum.github.io/browser-solidity/#version=soljson-v0.4.13+commit.fb4cb1a.js 
