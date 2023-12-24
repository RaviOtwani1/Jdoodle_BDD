@tag
  Feature: Verify Jdoodle code editor

    @tag2
    Scenario Outline: Navigating to Jdoodle code editor page

      Given Opening of Jdoodle <url>
      When  We check that the code editor page is open with default Java language
      Then  Page title is <title>
      When  User is entering text <searchText> to search bar
      Then  Search count <searchCount> should match as per the search string
      When User select a <searchText> from search drop
      Then Updated Page title is <languageTitle> and code is updated in the editor <languageCode>
      And Menu Items are displayed correctly
      And Login button displays "Sign in with Google" and "Sign in with Microsoft"

      Examples:
        |url                                              |title                          |searchText|searchCount|languageTitle                      |languageCode | codeOutput      |
        | https://www.jdoodle.com/online-java-compiler    |Online Java Compiler IDE       |C#        |2          |Online C# Compiler IDE             |using System;|Sum of x + y = 35|