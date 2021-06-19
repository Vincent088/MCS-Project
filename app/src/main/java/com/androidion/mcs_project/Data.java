package com.androidion.mcs_project;

    public class Data {
        private Integer id;
        private String tanggal;
        private String kegiatan;
        private String waktu;
        private String kuantitas;
        private String kalori;
        private String hasil;

        public Data (Integer id, String tanggal, String kegiatan, String waktu, String kuantitas, String kalori, String hasil){
            this.id = id;
            this.tanggal = tanggal;
            this.kegiatan = kegiatan;
            this.waktu = waktu;
            this.kuantitas = kuantitas;
            this.kalori = kalori;
            this.hasil = hasil;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTanggal() {
            return tanggal;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public String getKegiatan() {
            return kegiatan;
        }

        public void setKegiatan(String kegiatan) {
            this.kegiatan = kegiatan;
        }

        public String getWaktu() {
            return waktu;
        }

        public void setWaktu(String waktu) {
            this.waktu = waktu;
        }

        public String getKuantitas() {
            return kuantitas;
        }

        public void setKuantitas(String kuantitas) {
            this.kuantitas = kuantitas;
        }

        public String getKalori() {
            return kalori;
        }

        public void setKalori(String kalori) {
            this.kalori = kalori;
        }

        public String getHasil() {
            return hasil;
        }

        public void setHasil(String hasil) {
            this.hasil = hasil;
        }
    }



