var TableAdvanced = function () {

    var initTable1 = function() {

        /* Formatting function for row details */
        function fnFormatDetails ( oTable, nTr )
        {
            var aData = oTable.fnGetData( nTr );
            var sOut = '<table class="table table-bordered">';
            sOut += '<tr><td width="15%">PF Number:</td><td width="15%">'+aData[3]+'</td><td width="15%">Society No.:</td><td width="15%">'+aData[4]+'</td>';
            sOut +='<td width="15%">Member Type:</td><td width="15%">'+aData[5]+'</td></tr>';
            
            sOut += '<tr><td>Created :</td><td>'+aData[6]+'</td><td>Member Status :</td><td>'+aData[7]+'</td>';
            sOut += '<td>Father Name :</td><td>'+aData[8]+'</td></tr>';
            
            sOut += '<tr><td>Husband Name:</td><td>'+aData[9]+'</td><td>Date Of Birth:</td><td>'+aData[10]+'</td>';
            sOut += '<td>Gender:</td><td>'+aData[11]+'</td></tr>';
            
            sOut += '<tr><td>Marital Status:</td><td>'+aData[12]+'</td><td>Category :</td><td>'+aData[13]+'</td>';
            sOut += '<td>Pan No. :</td><td>'+aData[14]+'</td></tr>';
            
            sOut += '<tr><td>Aadhar No.:</td><td>'+aData[15]+'</td><td>Date of Appointment:</td><td>'+aData[16]+'</td>';
            sOut += '<td>Date of Joining:</td><td>'+aData[17]+'</td></tr>';
            
            
            sOut += '<tr><td>Service Duration :</td><td>'+aData[18]+'</td><td>Date of Resignation :</td><td>'+aData[19]+'</td>';
            sOut += '<td>Account No.:</td><td>'+aData[20]+'</td></tr>';
   
            sOut += '<tr><td>Branch :</td><td>'+aData[21]+'</td><td>Designation Type :</td><td>'+aData[22]+'</td>';
            sOut += '<td>Designation Level :</td><td>'+aData[23]+'</td></tr>';
         
            sOut += '<tr><td>Designation :</td><td>'+aData[24]+'</td><td>Department Name :</td><td>'+aData[25]+'</td>';
            sOut += '<td>Society :</td><td>'+aData[26]+'</td></tr>';
 
            sOut += '</table>';
             
            return sOut;
        }

        /*
         * Insert a 'details' column to the table
         */
        var nCloneTh = document.createElement( 'th' );
        var nCloneTd = document.createElement( 'td' );
        nCloneTd.innerHTML = '<span class="row-details row-details-close"></span>';
         
        $('#sample_1 thead tr').each( function () {
            this.insertBefore( nCloneTh, this.childNodes[0] );
        } );
         
        $('#sample_1 tbody tr').each( function () {
            this.insertBefore(  nCloneTd.cloneNode( true ), this.childNodes[0] );
        } );
         
        /*
         * Initialize DataTables, with no sorting on the 'details' column
         */
        var oTable = $('#sample_1').dataTable( {
            "aoColumnDefs": [
                {
                	"bSortable": false,
                	"aTargets": [ 0 ] 
                },
                {
                	"bVisible": false,
                	"aSearchable":true,
                	"aTargets": [ 7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26 ] 
                }
                
            ],
            "aaSorting": [[1, 'asc']],
             "aLengthMenu": [
                [5, 15, 20, -1],
                [5, 15, 20, "All"] // change per page values here
            ],
            // set the initial value
            "iDisplayLength": 10
        });

        jQuery('#sample_1_wrapper .dataTables_filter input').addClass("form-control input-medium input-inline"); // modify table search input
        jQuery('#sample_1_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown
        
         
        /* Add event listener for opening and closing details
         * Note that the indicator for showing which row is open is not controlled by DataTables,
         * rather it is done here
         */
        $('#sample_1').on('click', ' tbody td .row-details', function () {
            var nTr = $(this).parents('tr')[0];
            if ( oTable.fnIsOpen(nTr) )
            {
                /* This row is already open - close it */
                $(this).addClass("row-details-close").removeClass("row-details-open");
                oTable.fnClose( nTr );
            }
            else
            {
                /* Open this row */                
                $(this).addClass("row-details-open").removeClass("row-details-close");
                oTable.fnOpen( nTr, fnFormatDetails(oTable, nTr), 'details' );
            }
            
        });
        
        
    }

    
    
    return {

        //main function to initiate the module
        init: function () {
            
            if (!jQuery().dataTable) {
                return;
            }

            initTable1();
        }

    };

}();