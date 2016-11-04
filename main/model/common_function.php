<?php
/**
 * Created by PhpStorm.
 * User: JongYoon
 * Date: 2015-11-28
 * Time: 오후 2:22
 */



function DBconn(){
    return mysqli_connect("jycom.asuscomm.com", "root", "Cent12!@", "vending", 5066);
}

function redirect_to_view($func){
    return ("<script>location.replace('../view/mainView.php?func=$func')</script>");
}

function redirect_to_ctrl($func){
    return ("<script>location.replace('../controller/mainCtrl.php?func=$func')</script>");
}


function listValue($argMenu, $argTarget){

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

        case 4:{
            $table = "salesgraph";
            break;
        }

        default:{

        }
    }
    $query = "select * from ".$table;

    if($argTarget){
        switch ($argMenu){
            case 1:{
                $condition = "Pname";
                break;
            }

            case 2:{
                $condition = "Bname";
                break;
            }
            default:{

            }
        }
        $query .= " where ".$condition." = '".$argTarget."'";
    }

    $arrTemp = fetchValue($query);
    return $arrTemp;
}

function getSales($argInfo){
    $query = "select Pname from pstock";
    $pname = fetchValue($query);

    $sales = $pname;
    for($index_i = 0; $index_i < count($pname) ; $index_i++){
        $sales[$index_i]['sold'] = 0;
    }



    for($index_i = 0; $index_i < count($pname) ; $index_i++){
        for($index_j = 0; $index_j < count($argInfo) ; $index_j++){
            if($sales[$index_i]['Pname'] == $argInfo[$index_j]['product']){
                $sales[$index_i]['sold'] = ($sales[$index_i]['sold'] + ($argInfo[$index_j]['sold']));
            }
        }
    }

    return $sales;
}

function setSales($argInfo){
    for($index_i = 0 ; $index_i < count($argInfo) ; $index_i ++){
        $query = "update salesgraph set sold = '".$argInfo[$index_i]['sold']."' where product = '".$argInfo[$index_i]['Pname']."'";
        mysqli_query(DBconn(), $query);
    }
}



function fetchValue($argQuery){
    $result = mysqli_query(DBconn(), $argQuery);
    while($row = mysqli_fetch_array($result)){
        $arr[] = $row;
    }
    if(isset($arr)){
        return $arr;
    }

}