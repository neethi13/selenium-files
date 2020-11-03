$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/print.feature");
formatter.feature({
  "line": 2,
  "name": "Print values",
  "description": "print the index of BSE and NSE",
  "id": "print-values",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@tag"
    }
  ]
});
formatter.scenario({
  "comments": [
    {
      "line": 5,
      "value": "#@tag1"
    },
    {
      "line": 6,
      "value": "#Scenario: Print the values of BSE and NSE"
    },
    {
      "line": 7,
      "value": "#Given open browser"
    },
    {
      "line": 8,
      "value": "#And navigates to url"
    },
    {
      "line": 9,
      "value": "#When user performs required action"
    },
    {
      "line": 10,
      "value": "#Then successful validation and close browser"
    }
  ],
  "line": 12,
  "name": "Print the values of BSE and NSE",
  "description": "",
  "id": "print-values;print-the-values-of-bse-and-nse",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 11,
      "name": "@tag2"
    }
  ]
});
formatter.step({
  "line": 13,
  "name": "open browser",
  "keyword": "Given "
});
formatter.step({
  "line": 14,
  "name": "navigates to url",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "user prints href",
  "keyword": "When "
});
formatter.step({
  "line": 16,
  "name": "successful validation and close browser",
  "keyword": "Then "
});
formatter.match({
  "location": "AppPrint.open_browser()"
});
formatter.result({
  "duration": 6737684900,
  "status": "passed"
});
formatter.match({
  "location": "AppPrint.navigates_to_url()"
});
formatter.result({
  "duration": 4555812600,
  "status": "passed"
});
formatter.match({
  "location": "AppPrint.user_prints_href()"
});
formatter.result({
  "duration": 27956833800,
  "status": "passed"
});
formatter.match({
  "location": "AppPrint.successful_validation_and_close_browser()"
});
formatter.result({
  "duration": 7305077700,
  "status": "passed"
});
});