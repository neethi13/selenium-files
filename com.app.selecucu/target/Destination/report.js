$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/test.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "#Author: your.email@your.domain.com"
    },
    {
      "line": 2,
      "value": "#Keywords Summary :"
    },
    {
      "line": 3,
      "value": "#Feature: List of scenarios."
    },
    {
      "line": 4,
      "value": "#Scenario: Business rule through list of steps with arguments."
    },
    {
      "line": 5,
      "value": "#Given: Some precondition step"
    },
    {
      "line": 6,
      "value": "#When: Some key actions"
    },
    {
      "line": 7,
      "value": "#Then: To observe outcomes or validation"
    },
    {
      "line": 8,
      "value": "#And,But: To enumerate more Given,When,Then steps"
    },
    {
      "line": 9,
      "value": "#Scenario Outline: List of steps for data-driven as an Examples and \u003cplaceholder\u003e"
    },
    {
      "line": 10,
      "value": "#Examples: Container for s table"
    },
    {
      "line": 11,
      "value": "#Background: List of steps run before each of the scenarios"
    },
    {
      "line": 12,
      "value": "#\"\"\" (Doc Strings)"
    },
    {
      "line": 13,
      "value": "#| (Data Tables)"
    },
    {
      "line": 14,
      "value": "#@ (Tags/Labels):To group Scenarios"
    },
    {
      "line": 15,
      "value": "#\u003c\u003e (placeholder)"
    },
    {
      "line": 16,
      "value": "#\"\""
    },
    {
      "line": 17,
      "value": "## (Comments)"
    },
    {
      "line": 18,
      "value": "#Sample Feature Definition Template"
    }
  ],
  "line": 20,
  "name": "Verify login functionality of demo aut tours application",
  "description": "verify valid test data for login",
  "id": "verify-login-functionality-of-demo-aut-tours-application",
  "keyword": "Feature",
  "tags": [
    {
      "line": 19,
      "name": "@tag"
    }
  ]
});
formatter.scenario({
  "line": 23,
  "name": "valid login credentials",
  "description": "",
  "id": "verify-login-functionality-of-demo-aut-tours-application;valid-login-credentials",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 24,
  "name": "user opens browser",
  "keyword": "Given "
});
formatter.step({
  "line": 25,
  "name": "navigates to application url",
  "keyword": "And "
});
formatter.step({
  "line": 26,
  "name": "user navigates to register screen",
  "keyword": "When "
});
formatter.step({
  "line": 27,
  "name": "user should be able to create a new user",
  "keyword": "Then "
});
formatter.match({
  "location": "AppTest.user_opens_browser()"
});
formatter.result({
  "duration": 3992620769,
  "status": "passed"
});
formatter.match({
  "location": "AppTest.navigates_to_application_url()"
});
formatter.result({
  "duration": 3006640104,
  "status": "passed"
});
formatter.match({
  "location": "AppTest.user_navigates_to_register_screen()"
});
formatter.result({
  "duration": 96216791,
  "status": "passed"
});
formatter.match({
  "location": "AppTest.user_should_be_able_to_create_a_new_user()"
});
