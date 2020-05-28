<?php

$host = "localhost";
$user = "id13826075_uv";
$password = "GQHtOIpQt*Ld6]>%";
$database = "id13826075_hotel";


$conn = mysqli_connect($host, $user, $password, $database);

if(isset($_GET['hab'])){


  $query = "SELECT pluces,ptemperatura,pllaves,estado FROM Peticiones WHERE habitacion = '$_GET[hab]'";
  $sql_statement = $conn->prepare($query);
  $sql_statement->execute();
  $result = $sql_statement->get_result();

  while ($row = $result->fetch_assoc()) {

    $peticion[] = array_map('utf8_encode',$row);
  }

  if($result->num_rows>0){
    echo json_encode($peticion);
  }


}else{
  header('Location:index.php');
}

?>
