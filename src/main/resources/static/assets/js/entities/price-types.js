$(function(){

    //  DELETING COLLAR
    $('.my-price-type-to-delete').on('click', function (e){
        e.preventDefault();

        var $priceType = $(this);
        var priceTypeId = $priceType.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/price-types/" + priceTypeId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-price-type-id="' + priceTypeId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Price type #" + priceTypeId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of price type #" + priceTypeId);
            
            }
            
        });
        
    });

});
