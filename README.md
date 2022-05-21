# JavaJsonTools
![example workflow](https://github.com/SebChw/JavaJsonTools/actions/workflows/ci.yml/badge.svg)
[![GitHub version](https://img.shields.io/github/v/release/SebChw/JavaJsonTools?include_prereleases)](https://img.shields.io/github/v/release/SebChw/JavaJsonTools?include_prereleases)

## Introduction

Application for programmers who need to reformat or filter data structures saved in JSON format and to compare the structures with each other. JSON tools application allows you to both minify the unminified JSON representation, as well as reverse the operation (with any blanks and new lines added). The application is available via GUI and a remote API, so it can be integrated with existing tools.

---

Javadoc documentation: https://sebchw.github.io/JavaJsonTools/

Spreadsheet with Backlog: https://docs.google.com/spreadsheets/d/1z-Drdxqd3L3KFIRPDlmMciZyZRhIZvX5l9N-DH7EbVk/edit#gid=0

## Technologies
* Java 11
* Spring 2.3.3

## Features

* minify JSON
* extend JSON
* select given keys
* select all but given keys (reversed parameter set to true)

## API Quickstart

Create your requests using the *transform* endpoint.
Parameters:
* *json* - mandatory, json to be manipulated
* *transforms* - list of manipulations to be applied (reduce, extend, select). Extend by default.
* *keys* - keys to be seleced/dropped
* *reversed* - boolean flag for selection/dropping

## API POSTMAN example

A correct example request via POSTMAN

![Correct_request](/img/correct_api_request.png)

Obtained is an extended (by default) JSON of only "key" key.

A malformed request (missing comma)

![Incorrect_request](/img/incorrect_api_request.png)

## API Python example

```python
import requests

#good request we have data in correct json format
to_extend = {"abc": 213, "ade": 234}
r = requests.post("http://localhost:8080/json/transform/extend", json=to_extend)
print(r.status_code)
print(r.text)

#bad request we provide json as a string
r = requests.post("http://localhost:8080/json/transform/extend", json="{ 'abc': 23, 'adc': 'hello'}")
print(r.status_code)
print(r.text)
```

```bash
200
{
  "abc" : 213,
  "ade" : 234
}
400
{"timestamp":"2022-05-20T11:43:10.444+00:00","status":400,"error":"Bad Request","message":"","path":"/json/transform/extend"}
```

