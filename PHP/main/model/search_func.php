<?php
/**
 * Created by PhpStorm.
 * User: JongYoon
 * Date: 2015-10-26
 * Time: ���� 1:29
 */

session_start();


function search_product($argCode)
{
    $qurey = "select * from product where code in(select p_code from stock_001 where p_code = $argCode)";
    $_SESSION['search_Result'] = getDBValue(transmit_query(DB_Connect(), $qurey));
}

function search_sales($argCode){
    $qurey = "select * from product where code in (select p_code from sales where p_code = $argCode)";
    $_SESSION['search_Result'] = getDBValue(transmit_query(DB_Connect(), $qurey));
}

function search_bills($argCode){
    $qurey = "select * from currency where code in (select p_code from bill_1 where p_code = $argCode)";
    $_SESSION['search_Result'] = getDBValue(transmit_query(DB_Connect(), $qurey));
}

