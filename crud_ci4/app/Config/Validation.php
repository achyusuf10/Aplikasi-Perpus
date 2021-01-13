<?php

namespace Config;

class Validation
{
	//--------------------------------------------------------------------
	// Setup
	//--------------------------------------------------------------------

	/**
	 * Stores the classes that contain the
	 * rules that are available.
	 *
	 * @var array
	 */
	public $ruleSets = [
		\CodeIgniter\Validation\Rules::class,
		\CodeIgniter\Validation\FormatRules::class,
		\CodeIgniter\Validation\FileRules::class,
		\CodeIgniter\Validation\CreditCardRules::class,
	];

	/**
	 * Specifies the views that are used to display the
	 * errors.
	 *
	 * @var array
	 */
	public $templates = [
		'list'   => 'CodeIgniter\Validation\Views\list',
		'single' => 'CodeIgniter\Validation\Views\single',
	];

	//--------------------------------------------------------------------
	// Rules
	//--------------------------------------------------------------------

	public $user = [
		'nama'       => 'required',
		'jenis_kelamin'       => 'required',
		'alamat'       => 'required',
		'telp'       => 'required'
	];

	public $user_errors = [
		'nama' => [
			'required'  => 'Nama wajib diisi.'
		],
		'jenis_kelamin' => [
			'required'  => 'Jenis Kelamin wajib diisi.'
		],
		'alamat' => [
			'required'  => 'Alamat wajib diisi.'
		],
		'telp' => [
			'required'  => 'Telepon wajib diisi.'
		]
	];
}
