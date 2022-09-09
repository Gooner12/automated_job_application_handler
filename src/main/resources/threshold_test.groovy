def score = execution.getVariable("score")
def referral = execution.getVariable("has_referral")
def status = execution.getVariable("status")
def tailored_application = execution.getVariable("tailored_application")
def threshold_score
def conditional_score = execution.getVariable("add_threshold")

if (referral == "true" && (status.equals("Australian Citizen") || status.equals("Permanent Resident"))) {
    threshold_score = 17 + conditional_score
}

else if (referral == "false" && (status.equals("Australian Citizen") || status.equals("Permanent Resident"))) {
    threshold_score = 8 + conditional_score
}

else if (referral == "true" && status.equals("Working Visa")) {
    threshold_score = 15 + conditional_score
}

else if (referral == "false" && status.equals("Working Visa")) {
    threshold_score = 7 + conditional_score
}

// println(threshold_score)
// println(conditional_score)
// println(score)
if (score >= threshold_score) {
    execution.setVariable("vacancy_fulfilled", true)
}
else {
    execution.setVariable("vacancy_fulfilled", false)
}