<?php
/**
 * Created by PhpStorm.
 * User: JongYoon
 * Date: 2015-10-26
 * Time: ���� 1:21
 */

function DB_Connect(){
    return mysqli_connect("jycom.asuscomm.com", "vending", "DB!", "vending_machine", "3306");
}



function transmit_query($argQuery){
    return mysqli_query(DB_Connect(), $argQuery);
}


function getDBValue($argResult){
    return mysqli_fetch_array($argResult);
}

function getDBNumb($argResult){
    return mysqli_num_rows($argResult);
}

