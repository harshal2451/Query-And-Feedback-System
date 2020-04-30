function textin(){
    document.getElementById("comment_err").innerHTML = "";
}
function clicked() {
    document.getElementById("rating_err").innerHTML = "";
}
function submitFeedback(que_id){
    var r1 = document.getElementById("r1");
    var r2 = document.getElementById("r2");
    var r3 = document.getElementById("r3");
    var r4 = document.getElementById("r4");
    var score = 0.0;
    var cmt = document.getElementById("comments");
    if(r1.checked == true){
        document.getElementById("rating_err").innerHTML = "";
        score = 2.5;
    }
    else if(r2.checked == true){
        document.getElementById("rating_err").innerHTML = "";
        score = 5.0;
    }
    else if(r3.checked == true){
        document.getElementById("rating_err").innerHTML = "";
        score = 7.5;
    }
    else if(r4.checked == true){
        document.getElementById("rating_err").innerHTML = "";
        score = 10.0;
    }
    else{
        document.getElementById("rating_err").innerHTML = "<h6>Please select experience</h6>";
    }
    if(cmt.value.length == 0){
         document.getElementById("comment_err").innerHTML= "<h6>Please write comment so faculty can improve themself!</h6>";
    }
    else if(cmt.value.length < 5){
        document.getElementById("comment_err").innerHTML= "<h6>Please write comment with at least 5 length!</h6>";
    }
    else{
         if(score != 0){
            var comment = document.getElementById("comments").value;
            var xmlhttp = new XMLHttpRequest();
            alert(comment);
            xmlhttp.open("POST","SubmitFeedbackAnswer?f_rate="+score+"&f_comment="+comment+"&que_id="+que_id+"&admin_id="+admin_id,false);
            xmlhttp.send(null);
            var textres = xmlhttp.responseText;
             
            if(textres == "Success"){
                alert("Feedback submitted successfully");
                window.location.replace("my_questions.php");
            }
            else{
                alert(textres);
            }          
        }
    }
}