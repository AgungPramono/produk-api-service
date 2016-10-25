var aplikasiProduk = angular.module('aplikasiProduk',[]);

aplikasiProduk.controller("ProdukController", function ($http,$scope){
    $scope.dataProduk = {};
    
    $scope.tampilkanDataProduk = function(){
        $http.get("/api/produk/").then(sukses,gagal);
        
        function sukses(response){
            $scope.dataProduk = response.data;
            console.log($scope.dataProduk);
        };
        
        function gagal(response){
            console.log(response);
            alert("Gagal menampilkan data" + response);
        };
    };
    
    $scope.tampilkanDataProduk();
});