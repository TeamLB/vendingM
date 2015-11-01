<?php
/**
 * Created by PhpStorm.
 * User: JongYoon
 * Date: 2015-11-01
 * Time: 오후 2:56
 */


$data = "p1^1,p2^2,c2^1,c3^4";
//parse data
$data = explode(",",$data);

$p_count = 0;
$c_count = 0;
for($index_i = 0 ; $index_i < count($data) ; $index_i++){
    $cate = substr($data[$index_i],0,1);

    $arrSource = substr($data[$index_i],1);
    $arrTemp = explode("^",$arrSource);
    //echo array_shift($arrTemp)."<br>";
    //echo $arrTemp[0];
    //echo "<br>";
    switch ($cate){
        case "p":{
            $product[$p_count][0] = array_shift($arrTemp);
            $product[$p_count][1] = array_shift($arrTemp);
            $p_count++;
            break;
        }

        case "c":{
            $currency[$c_count][0] = array_shift($arrTemp);
            $currency[$c_count][1] = array_shift($arrTemp);
            $c_count++;
            break;
        }

        default:
    }
}



print_r($product);
echo "<br>";
print_r($currency);