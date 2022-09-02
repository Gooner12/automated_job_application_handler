def score = 10
def referral = true
def status = "Permanent Resident"
def tailored_application = true
def threshold_score
def conditional_score = execution.getVariable("add_threshold");

// if (referral == true && (status.equals("Australian Citizen") || status.equals("Permanent Resident"))) {
//     threshold_score = 17 + conditional_score
// }

// else if (referral == false && (status.equals("Australian Citizen") || status.equals("Permanent Resident"))) {
//     threshold_score = 8 + conditional_score
// }

// else if (referral == true && status.equals("Working Visa")) {
//     threshold_score = 15 + conditional_score
// }

// else if (referral == false && status.equals("Working Visa")) {
//     threshold_score = 7 + conditional_score
// }

if (referral == true) {
    threshold_score = 14 + conditional_score
}

else if (referral == false && (status.equals("Australian Citizen") || status.equals("Permanent Resident"))) {
    threshold_score = 5 + conditional_score
}
println(threshold_score)
if (score >= threshold_score) {
    execution.setVariable("vacancy_fulfilled", true)
}
else {
    execution.setVariable("vacancy_fulfilled", false)
}