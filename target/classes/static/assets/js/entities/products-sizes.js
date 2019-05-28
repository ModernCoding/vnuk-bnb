$(function(){

    //  DELETING SUBCATEGORY
    $('.my-product-size-to-delete').on('click', function (e){
        e.preventDefault();

        var $productsSize = $(this);
        var productsSizeId = $productsSize.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/products-sizes/" + productsSizeId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-product-size-id="' + productsSizeId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Product size #" + productsSizeId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of product size #" + productsSizeId);
            
            }
            
        });
        
    });

});
