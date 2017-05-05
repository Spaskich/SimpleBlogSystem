/**
 * Created by Spaskich on 4.5.2017 г..
 */
if ($("#comments").length == 1) {

    var refreshComments = function refreshComments() {
        var $comments = $("#comments");

        $.ajax({
            type : "GET",
            // contentType: "text/html",
            url : "/api/article/" + $comments.data("post-id") + "/comments",
            dataType : 'html',
            success : function(data) {
                $comments.html(data);
            },
            error : function(e) {
                console.log("ERROR: ", e);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
    };

    setInterval(function () {
        refreshComments();
    },
        1000)
}

if ($("#postCommentForm")) {
    $("#postCommentForm").submit(function (e) {

        e.preventDefault();

        var data = {
            post_id: $("#comments").data("post-id"),
            text: $("#commentText").val()
        };

        if (data.text.trim().length < 3) {
            alert("Comments must be at least 3 symbols long.");
            return;
        }

        $.ajax({
            type : "POST",
            url : "/api/article/" + data.post_id + "/comments",
            data: data,
            dataType : 'html',
            success : function(data) {
                $("#commentText").val("");
            },
            error : function(e) {
                console.log("ERROR: ", e);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
    });
}