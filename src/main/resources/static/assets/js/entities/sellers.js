$(function(){

    //  DELETING SELLER
    $('.my-seller-to-delete').on('click', function (e){
        e.preventDefault();

        var $seller = $(this);
        var sellerId = $seller.val();


        $.ajax({

            type: "DELETE",
            url: "/sellers/" + sellerId,

            data: {},

            success: function() {

                $('tr[data-seller-id="' + sellerId + '"]').remove();

                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Seller #" + sellerId + " has successfully been deleted.");

            },


            error: function() {

                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of seller #" + sellerId);

            }

        });

    });

});
