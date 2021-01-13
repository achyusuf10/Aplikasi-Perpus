<?php

namespace App\Models;

use CodeIgniter\Model;

class User_model extends Model
{

    protected $table = 'users';

    public function getUser($id = false)
    {
        if ($id === false) {
            return $this->findAll();
        } else {
            return $this->getWhere(['id' => $id])->getRowArray();
        }
    }
    public function insertUser($data)
    {
        return $this->db->table($this->table)->insert($data);
    }

    public function updateUser($data, $id)
    {
        return $this->db->table($this->table)->update($data, ['id' => $id]);
    }

    public function deleteUser($id)
    {
        return $this->db->table($this->table)->delete(['id' => $id]);
    }
}
