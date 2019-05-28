$(function(){

    //  DELETING CUSTOMER
    $('.my-customer-to-delete').on('click', function (e){
        e.preventDefault();

        var $customer = $(this);
        var customerId = $customer.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/customers/" + customerId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-customer-id="' + customerId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Customer #" + customerId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of customer #" + customerId);
            
            }
            
        });
        
    });

});
