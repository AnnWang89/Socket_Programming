package socketprogramming;
import java.net.*;
import java.io.*;
/**
* �q�Ʀr�Ȥ��
*/
public class TCPClient {
	public static void main(String[] args) {
			Socket socket = null;
			OutputStream os = null;
			InputStream is = null;
			BufferedReader br = null;
			byte[] data = new byte[2];
			try{
//�إ߳s��
				socket = new Socket("127.0.0.1",10001);
//�o�e�ƾ�
				os= socket.getOutputStream();
//Ū�����X�ƾ�
				is = socket.getInputStream();
//��L��J�y
				br = new BufferedReader(new InputStreamReader(System.in));
//�h����J
				while(true){
					System.out.println("�п�J�Ʀr�G");
//������J
					String s = br.readLine();

//��������
					if(s.equals("quit")){
						os.write("quit".getBytes());
						break;
					}
//�����J�O�_�X�k
					boolean b = true;
					try{
						Integer.parseInt(s);
					}catch(Exception e){
						b = false;
					}
					if(b){ //��J�X�k
//�o�e�ƾ�
						os.write(s.getBytes());
//�������X
						is.read(data);
//�P�_
						switch(data[0]){
						case 0:
							System.out.println("�۵��I���P�A�I");
							break;
						case 1:
							System.out.println("�j�F�I");
							break;
						case 2:
							System.out.println("�p�F�I");
							break;
						default:
							System.out.println("�䥦���~�I");
						}
//���ܲq������
						System.out.println("�A�w�g�q�F"+ data[1] +"���I");
//�P�_���ƬO�_�F��5��
						if(data[1] >= 5){
							System.out.println("�A���F�I");
	//�����A���ݽu�{���������|
							os.write("quit".getBytes());
	//�����Ȥ�ݵ{��
							break;
						}
					}else{ //��J���~
						System.out.println("��J���~�I");
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
//�����s��
					br.close();
					is.close();
					os.close();
					socket.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
}