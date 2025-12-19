#!/bin/bash

mvn clean test allure:report
open target/site/allure-maven-plugin/index.html

