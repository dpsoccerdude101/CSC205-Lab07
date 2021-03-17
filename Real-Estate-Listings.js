var queryString;
var called = false;
function validateForm() {
  var formElement = document.forms.RealEstateForm;
  var formData = new FormData(formElement);
  var keypairs = [];
  let count = 0;
  for (var pair of formData.entries()) {
    if (pair[1] == "" || pair[1] < 0) {
      if (count % 2 == 0) {
        //sets values of input names with 'min' to 0 if null, or less than 0
        pair[1] = 0;
      } else if (count == 1)
        //sets value of maxPrice to 1 million if null, or less than 0
        pair[1] = 1000000;
      else if (count == 3)
        //sets value of maxArea to 6000 if null, or less than 0
        pair[1] = 6000;
      //sets value of maxBeds to 10 if null, or less than 0
      else pair[1] = 10;
    }
    keypairs.push(pair[0] + "=" + pair[1]);
    count++;
  }
   queryString = keypairs.join("&");
  console.log(queryString);
  //prevent form submission
  sendToJava(queryString);

  if (called) {
    document.getElementById("dream").removeAttribute("type", "submit");
    document.getElementById("dream").setAttribute("disabled", "");
    called = false;
  }
    
  return false;
};
 function sendToJava (str) {
    javaConnector.toJava(str);
}; 

var jsConnector = {
    showResult: function (result) {
        document.getElementById('output').innerHTML = result;
    }
};
function getJsConnector() {
    return jsConnector;
};
function Reset() {
    document.getElementById("notDream").setAttribute("disabled", "");
    document.getElementById("dream").setAttribute("type", "submit");
    document.getElementById("notDream").removeAttribute("type", "submit");
    document.getElementById("dream").removeAttribute("disabled", "");
};
function remove() {
  document.getElementById("notDream").removeAttribute("disabled");
  document.getElementById("notDream").setAttribute("type", "submit");
  called = true;
};
const form = document.getElementById("RealEstateForm");
form.onreset = Reset;