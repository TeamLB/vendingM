<?php
/**
 * Created by PhpStorm.
 * User: JongYoon
 * Date: 2015-11-28
 * Time: 오후 2:22
 */



function DBconn(){
    return mysqli_connect("jycom.asuscomm.com", "vending", "vending", "vendingmachine", 3306);
}

function redirect_to_view($func){
    return ("<script>location.replace('../view/mainView.php?func=$func')</script>");
}

function redirect_to_ctrl($func){
    return ("<script>location.replace('../controller/mainCtrl.php?func=$func')</script>");
}


function listValue($argMenu){

    switch($argMenu){
        case 1:{
            $table = "pstock";
            break;
        }

        case 2:{
            $table = "bstock";
            break;
        }

        case 3:{
            $table = "salesvolume";
            break;
        }
        default:{

        }
    }
    $query = "select * from ".$table;
    $arrTemp = fetchValue($query);
    return $arrTemp;
}

function fetchValue($argQuery){
    $result = mysqli_query(DBconn(), $argQuery);
    while($row = mysqli_fetch_array($result)){
        $arr[] = $row;
    }
    return $arr;
}