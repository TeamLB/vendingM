<?php
/**
 * Created by PhpStorm.
 * User: JongYoon
 * Date: 2015-11-28
 * Time: 오후 1:57
 */
session_start();
include_once (dirname(__FILE__)."/../model/common_function.php");
include_once (dirname(__FILE__)."/../model/modify.php");


$func = isset($_REQUEST['func']) ? $_REQUEST['func'] : null;
$menu = intval($func/100);
switch($menu){
    case 1:{
        if($func == 100){
            $arrTemp = listValue($menu);
            $_SESSION['info_list'] = $arrTemp;
            echo redirect_to_view($func);
        }

        elseif($func == 110){
            $target = isset($_REQUEST['target']) ? $_REQUEST['target'] : null;
            $info['Pname'][] = isset($_REQUEST['Pname']) ? $_REQUEST['Pname'] : null;
            //$info['Pimage'][] = isset($_POST['Pimage']) ? $_POST['Pimage'] : null;
            $info['Pprice'][] = isset($_REQUEST['Pprice']) ? $_REQUEST['Pprice'] : null;
            $info['Pstock'][] = isset($_REQUEST['Pstock']) ? $_REQUEST['Pstock'] : null;

            echo "<br>".$target."<br><br><br>";

            if(!$info){
                die("No info");
            }
            $arrTemp = modify($info, $target, $menu);
            $_SESSION['info_list'] = $arrTemp;
            $func += 10;
            echo redirect_to_ctrl($func);
        }

        elseif($func == 120){
            $arrTemp = listValue($menu);
            $_SESSION['info_list'] = $arrTemp;
            echo redirect_to_view($func);
        }

        break;
    }

    case 2:{

        if($func == 200){
            $arrTemp = listValue($menu);
            $_SESSION['info_list'] = $arrTemp;
            print_r($_SESSION['info_list']);
            echo redirect_to_view($func);
        }

        elseif($func == 210){
            $target = isset($_REQUEST['target']) ? $_REQUEST['target'] : null;
            $info['Bname'][] = isset($_REQUEST['Bname']) ? $_REQUEST['Bname'] : null;
            $info['Bstock'][] = isset($_REQUEST['Bstock']) ? $_REQUEST['Bstock'] : null;

            if(!$info){
                die("No info");
            }
            $arrTemp = modify($info, $target, $menu);
            $_SESSION['info_list'] = $arrTemp;
            $func += 10;
            echo redirect_to_ctrl($func);
        }

        elseif($func == 220){
            $arrTemp = listValue($menu);
            $_SESSION['info_list'] = $arrTemp;
            echo redirect_to_view($func);
        }
        break;
    }

    case 3:{
        $arrTemp = listValue($menu);
        $_SESSION['info_list'] = $arrTemp;
        echo redirect_to_view($func);
        break;
    }

    default:{
        echo redirect_to_view($func);
        break;
    }
}