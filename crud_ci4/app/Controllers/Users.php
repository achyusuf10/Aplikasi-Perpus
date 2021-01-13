<?php

namespace App\Controllers;

use CodeIgniter\RESTful\ResourceController;

class Users extends ResourceController
{
    protected $format       = 'json';
    protected $modelName    = 'App\Models\User_model';

    public function index()
    {
        //return $this->respond($this->model->findAll(), 200);
        $result = $this->model->findAll();
        $response = [
            'status' => 200,
            'error' => false,
            'message' => 'Berhasil mengambil data',
            'data' => $result,
        ];
        return $this->respond($response, 200);
    }

    public function create()
    {
        $validation =  \Config\Services::validation();
        $jabatan  = $this->request->getPost('jabatan');
        $nama  = $this->request->getPost('nama');
        $nim  = $this->request->getPost('nim');
        $jenis_kelamin = $this->request->getPost('jenis_kelamin');
        $alamat  = $this->request->getPost('alamat');
        $telp  = $this->request->getPost('telp');
        // $file_image = $this->request->getFile('image');
        // $file_image->move(ROOTPATH . 'upload');
        // $nama_gambar = $file_image->getName();

        $data = [
            'jabatan' => $jabatan,
            'nama' => $nama,
            'nim' => $nim,
            'jenis_kelamin' => $jenis_kelamin,
            'alamat' => $alamat,
            'telp' => $telp,
            //'image' => $nama_gambar

        ];
        $simpan = $this->model->insertUser($data);
        if ($simpan) {
            $response = [
                'status' => 200,
                'error' => false,
                'message' => 'Berhasil Menambah Data User',
            ];
            return $this->respond($response, 200);
        }
    }

    public function show($id = NULL)
    {
        $get = $this->model->getUser($id);
        if ($get) {
            $code = 200;
            $response = [
                'status' => $code,
                'error' => false,
                'message' => 'Data Berhasil Diambil',
                'data' => $get,
            ];
        } else {
            $code = 401;
            $response = [
                'status' => $code,
                'error' => true,
                'message' => 'Data Tidak Ditemukan',
            ];
        }
        return $this->respond($response, $code);
    }

    public function edit($id = NULL)
    {
        $get = $this->model->getUser($id);
        if ($get) {
            $code = 200;
            $response = [
                'status' => $code,
                'error' => false,
                'message' => 'Data Berhasil Diambil',
                'data' => $get,
            ];
        } else {
            $code = 401;
            $response = [
                'status' => $code,
                'error' => true,
                'message' => 'Data Tidak Ditemukan',
            ];
        }
        return $this->respond($response, $code);
    }

    public function update($id = NULL)
    {
        $validation =  \Config\Services::validation();

        $data = $this->request->getRawInput();

        if ($validation->run($data, 'user') == FALSE) {

            $response = [
                'status' => 500,
                'error' => true,
                'message' => $validation->getErrors(),
            ];
            return $this->respond($response, 500);
        } else {

            $simpan = $this->model->updateUser($data, $id);
            if ($simpan) {
                $response = [
                    'status' => 200,
                    'error' => false,
                    'message' => 'Berhasil Update Data User',
                ];
                return $this->respond($response, 200);
            }
        }
    }

    public function delete($id = NULL)
    {
        $data = $this->model->getUser($id);
        if (empty($data)) { // jika data kosong atau tidak tersedia
            $code = 401;
            $response = [
                'status' => $code,
                'error' => true,
                'message' => 'Data Tidak Ditemukan',
            ];
        } else { // jika data ditemukan
            $hapus = $this->model->deleteUser($id);
            if ($hapus) {
                $code = 200;
                $response = [
                    'status' => $code,
                    'error' => false,
                    'message' => 'Berhasil Menghapus Data',
                ];
            } else {
                $code = 401;
                $response = [
                    'status' => $code,
                    'error' => true,
                    'message' => 'Gagal Menghapus Data',
                ];
            }
        }
        return $this->respond($response, $code);
    }
}
