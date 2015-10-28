<?php
/**
 * Created by PhpStorm.
 * User: JongYoon
 * Date: 2015-10-26
 * Time: ���� 1:30
 */



/*status :
*   0  -> Success
 *  1  -> Password error
 *  -1 -> ID error
*/

function login($argID, $argPD)
{
    $query = "select * from user where id = " . $argID;

    if (transmit_query(DB_Connect(), $query) == true){
        $query = "select * from user where password = " . $argPD;
        if (transmit_query(DB_Connect(), $query) == true) {
            return 0;
        } else {
            return 1;
        }
    }

    else{
      return -1;
    }




}