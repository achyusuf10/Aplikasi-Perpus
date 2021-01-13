<?php

namespace App\Database\Seeds;

class UserSeeder extends \CodeIgniter\Database\Seeder
{
    public function run()
    {
        $data1 = [
            'nama'   => 'Yusuf',
            'jenis_kelamin' => 'Laki-Laki',
            'alamat' => 'Jombang',
            'telp' => '08000000'
        ];
        $data2 = [
            'nama'   => 'Test',
            'jenis_kelamin' => 'Laki-Laki',
            'alamat' => 'Gresik',
            'telp' => '08000000'
        ];
        $this->db->table('users')->insert($data1);
        $this->db->table('users')->insert($data2);
    }
}
