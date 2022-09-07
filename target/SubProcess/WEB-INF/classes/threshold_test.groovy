def score = 10
def referral = false
def status = "Permanent Resident"
def tailored_application = true
def threshold_score

// if (referral == true && (status.equals("Australian Citizen") || status.equals("Permanent Resident"))) {
//     threshold_score = 17
// }

// else if (referral == false && (status.equals("Australian Citizen") || status.equals("Permanent Resident"))) {
//     threshold_score = 8
// }

// else if (referral == true && status.equals("Working Visa")) {
//     threshold_score = 15
// }

// else if (referral == false && status.equals("Working Visa")) {
//     threshold_score = 7
// }

if (referral == true) {
    threshold_score = 14
}

else if (referral == false && (status.equals("Australian Citizen") || status.equals("Permanent Resident"))) {
    threshold_score = 5
}
println(threshold_score)
if (score >= threshold_score) {
    execution.setVariable("vacancy_fulfilled", true)
}
else {
    execution.setVariable("vacancy_fulfilled", false)
}