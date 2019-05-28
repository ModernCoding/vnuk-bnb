$(function(){

    //  DELETING SLEEVE
    $('.my-sleeve-to-delete').on('click', function (e){
        e.preventDefault();

        var $sleeve = $(this);
        var sleeveId = $sleeve.val();


        $.ajax({

            type: "DELETE",
            url: "/sleeves/" + sleeveId,

            data: {},

            success: function() {

                $('tr[data-sleeve-id="' + sleeveId + '"]').remove();

                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Sleeve #" + sleeveId + " has successfully been deleted.");

            },


            error: function() {

                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of sleeve #" + sleeveId);

            }

        });

    });

});
