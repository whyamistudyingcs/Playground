'''shell
openssl s_client -connect www.godaddy.com:443 -showcerts </dev/null > cert_output.txt
nano gdroot-g2.crt # to copy cert info or u can directly pipe out into cert_output.crt
sudo cp gdroot-g2.crt /etc/pki/ca-trust/source/anchors/
sudo update-ca-trust extract
curl -v https://www.godaddy.com
trust list | grep -i "Go Daddy"
'''
