<?php
/*
 * Created by PhpStorm.
 * User: JongYoon
 * Date: 2015-11-28
 * Time: 오후 2:22
 */

$list = isset($_SESSION['info_list']) ? $_SESSION['info_list'] : null;
?>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <style type="text/css">

    </style>
    <script type="text/javascript">
        $(function () {
            $('#container').highcharts({
                chart: {
                    type: 'pie',
                    options3d: {
                        enabled: true,
                        alpha: 45,
                        beta: 0
                    }
                },
                title: {
                    text: '제품별 판매율'
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        depth: 35,
                        dataLabels: {
                            enabled: true,
                            format: '{point.name}'
                        }
                    }
                },
                series: [{
                    type: 'pie',
                    name: '판매율',
                    data: [
                        <?php
                            for($index_i = 0 ; $index_i < count($list) ; $index_i++){
                             if($list[$index_i]['share'] !=0){
                                    if($index_i != count($list)-1){
                                        echo "['";
                                        echo $list[$index_i]['product']."', ".$list[$index_i]['share'];
                                        echo "],";
                                    }

                                    else{
                                        echo "{";
                                         echo "name: '".$list[$index_i]['product']."',";
                                            echo "y: '".$list[$index_i]['share']."',";
                                            echo "sliced: true,";
                                            echo "selected: true";
                                        echo "}";
                                    }
                                }
                            }
                        ?>
                    ]
                }]
            });
        });

    </script>
</head>
<body>
<script src="../js/highcharts.js"></script>
<script src="../js/highcharts-3d.js"></script>
<script src="../js/exporting.js"></script>

<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
<br>
<span><a href="../controller/mainCtrl.php?func=300">리스트</a></span>
<span><a href="../controller/mainCtrl.php?func=310">그래프</a></span><br>
<a href="../controller/mainCtrl.php?func">메인 화면</a><br>
</body>
</html>
