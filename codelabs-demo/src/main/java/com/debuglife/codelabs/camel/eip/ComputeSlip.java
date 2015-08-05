package com.debuglife.codelabs.camel.eip;


public class ComputeSlip {
    public String compute(String body) {
        String answer = "mock:a";
        if (body.contains("Cool")) {
        answer += ",mock:b";
        }
        answer += ",mock:c";
        return answer;
        }
}
