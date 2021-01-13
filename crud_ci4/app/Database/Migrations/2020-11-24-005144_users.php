<?php

namespace App\Database\Migrations;

use CodeIgniter\Database\Migration;

class Users extends Migration
{
	public function up()
	{
		$this->forge->addField([
			'id'           => [
				'type'              => 'INT',
				'constraint'        => 8,
				'unsigned'          => TRUE,
				'auto_increment'    => TRUE
			],
			'nama'         => [
				'type'              => 'VARCHAR',
				'constraint'        => '50',
			],
			'jenis_kelamin'         => [
				'type'              => 'VARCHAR',
				'constraint'        => '10',
			],
			'alamat'         => [
				'type'              => 'VARCHAR',
				'constraint'        => '50',
			],
			'telp'         => [
				'type'              => 'VARCHAR',
				'constraint'        => '13',
			],

		]);
		$this->forge->addKey('id', TRUE);
		$this->forge->createTable('users');
	}

	//--------------------------------------------------------------------

	public function down()
	{
		//
	}
}
