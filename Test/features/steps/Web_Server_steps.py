from behave import when, then
import requests
import re
from datetime import datetime
from requests.exceptions import RequestException


@when('I request the "{route}" route')
def step_request_route(context, route):
    try:
        context.response = requests.get(f"http://localhost:8080/api{route}")
    except RequestException:
        assert False, "Unexpected Connection error occurred"
    except Exception as e:
        assert False, f"Unexpected error occurred: {str(e)}"


@then('the response should have a status code of {status_code:d}')
def step_check_status_code(context, status_code):
    try:
        assert context.response.status_code == status_code
    except AssertionError:
        raise AssertionError(f"Expected status code: {status_code}. Actual status code: {context.response.status_code}")


@then('the response should contain at least one log')
def step_check_log_presence(context):
    response_data = context.response.json()
    assert len(response_data) > 0, "No logs found in the response"


@then('each log should have a valid ID, time, date, and method')
def step_check_log_properties(context):
    response_data = context.response.json()
    logs = response_data

    for log in logs:
        assert re.match(r'^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$',
                        log['id']), f"Invalid log ID: {log['id']}"

        try:
            log_time = datetime.strptime(log['time'], "%H:%M:%S")
            assert isinstance(log_time, datetime), f"Invalid log time: {log['time']}"
        except ValueError:
            assert False, f"Invalid log time format: {log['time']}"

        try:
            log_date = datetime.strptime(log['date'], "%Y-%m-%d")
            assert isinstance(log_date, datetime), f"Invalid log date: {log['date']}"
        except ValueError:
            assert False, f"Invalid log date format: {log['date']}"

        assert log['method'] in ['SeedClass', 'addLog'], f"Invalid log method: {log['method']}"


@then('a new log should be created')
def step_check_new_log(context):
    print(context)
