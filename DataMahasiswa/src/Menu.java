import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Menu extends JFrame{
    public static void main(String[] args) {
        // buat object window
        Menu window = new Menu();

        // atur ukuran window
        window.setSize(560, 560);
        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);
        // isi window
        window.setContentPane(window.mainPanel);
        // ubah warna background
        window.getContentPane().setBackground(Color.white);
        // tampilkan window
        window.setVisible(true);
        // agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;

    private Database database;

    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JComboBox programStudiComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JLabel programStudiLabel;

    // constructor
    public Menu() {
        // inisialisasi listMahasiswa
        listMahasiswa = new ArrayList<>();

        database = new Database();

        // isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] jenisKelaminData = {"", "Laki-laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));

        //atur isi combo box
        String[] programStudiData = {"", "Ilmu Komputer", "Pendidikan Ilmu Komputer"};
        programStudiComboBox.setModel(new DefaultComboBoxModel(programStudiData));

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                }else{
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    deleteData();
                }
            }
        });
        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();

                // simpan value textfield dan combo box
                String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
                String selectedNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
                String selectedJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 3).toString();
                String selectedProgramStudi = mahasiswaTable.getModel().getValueAt(selectedIndex, 4).toString();

                // ubah isi textfield dan combo box
                nimField.setText(selectedNim);
                namaField.setText(selectedNama);
                jenisKelaminComboBox.setSelectedItem(selectedJenisKelamin);
                programStudiComboBox.setSelectedItem(selectedProgramStudi);


                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");
                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] column = {"No", "NIM", "Nama", "Jenis Kelamin", "Program Studi"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel temp = new DefaultTableModel(null, column);
        // isi tabel dengan listMahasiswa
        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM mahasiswa");

            int i = 0;
            while (resultSet.next()) {
                Object[] row = new Object[5];
                row[0] = i + 1;
                row[1] = resultSet.getString("nim");
                row[2] = resultSet.getString("nama");
                row[3] = resultSet.getString("jenis_kelamin");
                row[4] = resultSet.getString("program_studi");

                temp.addRow(row);
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return temp; // return juga harus diganti
    }

    public void insertData() {
        // ambil value dari textfield dan combobox
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String programStudi = programStudiComboBox.getSelectedItem().toString();

        // Validasi input kosong
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || programStudi.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Harap isi semua kolom!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cek apakah NIM sudah ada dalam database
        String checkQuery = "SELECT COUNT(*) FROM mahasiswa WHERE nim = '" + nim + "';";
        try {
            ResultSet rs = database.selectQuery(checkQuery);
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "NIM sudah terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat memeriksa NIM!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }

        String sql = "INSERT INTO mahasiswa VALUES (null, '" + nim + "', '" + nama + "', '" + jenisKelamin + "', '" + programStudi + "');";
        database.insertUpdateDeleteQuery(sql);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Insert berhasil");
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
    }

    public void updateData() {
        try {
            String nim = nimField.getText();
            String nama = namaField.getText();
            String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
            String programStudi = programStudiComboBox.getSelectedItem().toString();

            // Validasi NIM tidak boleh kosong
            if (nim.isEmpty()) {
                JOptionPane.showMessageDialog(null, "NIM tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 🔹 Cek apakah NIM ada di database
            String checkSql = "SELECT COUNT(*) AS total FROM mahasiswa WHERE nim = '" + nim + "'";
            ResultSet rs = database.selectQuery(checkSql);

            if (rs.next()) {
                int total = rs.getInt("total");
                if (total == 0) {
                    JOptionPane.showMessageDialog(null, "NIM tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
                    rs.close();
                    return;
                }
            }
            rs.close(); // Tutup result set

            // 🔹 Query UPDATE
            String sql = "UPDATE mahasiswa SET nama = '" + nama +
                    "', jenis_kelamin = '" + jenisKelamin +
                    "', program_studi = '" + programStudi +
                    "' WHERE nim = '" + nim + "'";

            int rowsUpdated = database.insertUpdateDeleteQuery(sql);

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil diperbarui!");
            } else {
                JOptionPane.showMessageDialog(null, "Update gagal: Terjadi kesalahan.");
            }

            mahasiswaTable.setModel(setTable());

            clearForm();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
        }
    }


    public void deleteData() {
        String nim = nimField.getText();

        if (nim.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int option = JOptionPane.showConfirmDialog(null, "Apakah benar ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM mahasiswa WHERE nim = '" + nim + "'";

            int rowsDeleted = database.insertUpdateDeleteQuery(sql);

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(null, "Penghapusan gagal: NIM tidak ditemukan.");
            }

            // Perbarui tabel
            mahasiswaTable.setModel(setTable());

            // Bersihkan form
            clearForm();
        }
    }

    public void clearForm() {
        // kosongkan semua texfield dan combo box
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedItem("");
        programStudiComboBox.setSelectedItem("");

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");
        // sembunyikan button delete
        deleteButton.setVisible(false);
        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }

    private void populateList() {
        listMahasiswa.add(new Mahasiswa("2203999", "Amelia Zalfa Julianti", "Perempuan", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2202292", "Muhammad Iqbal Fadhilah", "Laki-laki", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2202346", "Muhammad Rifky Afandi", "Laki-laki", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2210239", "Muhammad Hanif Abdillah", "Laki-laki", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2202046", "Nurainun", "Perempuan", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2205101", "Kelvin Julian Putra", "Laki-laki", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2200163", "Rifanny Lysara Annastasya", "Perempuan", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2202869", "Revana Faliha Salma", "Perempuan", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2209489", "Rakha Dhifiargo Hariadi", "Laki-laki", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2203142", "Roshan Syalwan Nurilham", "Laki-laki", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2200311", "Raden Rahman Ismail", "Laki-laki", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2200978", "Ratu Syahirah Khairunnisa", "Perempuan", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2204509", "Muhammad Fahreza Fauzan", "Laki-laki", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2205027", "Muhammad Rizki Revandi", "Laki-laki", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2203484", "Arya Aydin Margono", "Laki-laki", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2200481", "Marvel Ravindra Dioputra", "Laki-laki", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2209889", "Muhammad Fadlul Hafiizh", "Laki-laki", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2206697", "Rifa Sania", "Perempuan", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2207260", "Imam Chalish Rafidhul Haque", "Laki-laki", "Ilmu Komputer"));
        listMahasiswa.add(new Mahasiswa("2204343", "Meiva Labibah Putri", "Perempuan", "Ilmu Komputer"));
    }
}
