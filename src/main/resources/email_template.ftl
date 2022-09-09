<html>
<head>
  <title>Job Application Outcome</title>
</head>
<body style="font-family:tahoma;">
  <p>
    Hi ${name},
  </p>
  <div>
    <p>${message}</p>
    <#if reason == "found high scoring candidate">
      <ul>
        <li>Job Requirement: Met</li>
        <li>Application Tailored: ${tailored_application}</li>
        <li>Considered for interview: Yes</li>
        <li>Last Status: Waitlisted</li>
        <li>Job Competition: ${competition}</li>
      </ul>
    </#if>
    <#if feedback != "">
      <p style="margin-bottom:20px">${feedback}</p> 
    </#if>
  </div>
  <div>
    Kind regards,<br>
    Hiring Manager<br>
    XYZ Company
  </div>
  <div style="margin-top:15px">
    <img src="https://i.ibb.co/Z8BnGQ4/banner-g40cdafdcc-1920.jpg" alt="banner" style="width:65vw; height: 30vh;">
  </div
</body>
</html>