package com.tcc2.bke_auth4isp.panel_client_home.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.tcc2.bke_auth4isp.R;
import com.tcc2.bke_auth4isp.analytic_logs.YLog;
import com.tcc2.bke_auth4isp.common.SecurityUtilities;
import com.tcc2.bke_auth4isp.common.AES;
import com.tcc2.bke_auth4isp.dialogs.ConfirmAuthenticationDialog;
import com.tcc2.bke_auth4isp.dialogs.ErrorHMACDialog;
import com.tcc2.bke_auth4isp.dialogs.LoadingDialog;
import com.tcc2.bke_auth4isp.entity.AuthSolicitation;
import com.tcc2.bke_auth4isp.entity.Person;
import com.tcc2.bke_auth4isp.entity.Technician;
import com.tcc2.bke_auth4isp.panel_client.view.PanelClientActivity;
import com.tcc2.bke_auth4isp.panel_client_home.ClientHomeContracts;
import com.tcc2.bke_auth4isp.panel_client_home.presenter.PanelClientHomePresenter;
import com.tcc2.bke_auth4isp.panel_client_home.router.PanelClientHomeRouter;

import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class HomeFragment extends Fragment implements ClientHomeContracts.View {

    Button buttonGenerateQRCode;
    Button buttonAuthenticationTechnican;
    ClientHomeContracts.Presenter presenter;
    ClientHomeContracts.Router router;
    TextView name_client;
    Person person;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        router = new PanelClientHomeRouter(getContext());
        presenter = new PanelClientHomePresenter(this);
        setupUI(root);
        return root;
    }

    private void setupUI(View container) {
        name_client = container.findViewById(R.id.client_name);
        person = (Person) getActivity().getIntent().getSerializableExtra("PERSON");
        name_client.setText(person.getName() + "!");
        buttonGenerateQRCode = container.findViewById(R.id.buttonGenerateQRCode);
        buttonAuthenticationTechnican = container.findViewById(R.id.buttonAuthenticationTechnican);
        buttonGenerateQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                router.gotoGenerateQRCode();
                Toast.makeText(getContext(), "Este fluxo de autentica????o n??o est?? dispon??vel neste prot??tipo. O cliente deve ler o c??digo de autentica????o do t??cnico.", Toast.LENGTH_SHORT).show();
            }
        });
        HomeFragment homeFragment = this;
        buttonAuthenticationTechnican.setOnClickListener(new View.OnClickListener() {
                                                             @Override
                                                             public void onClick(View v) {
                                                                 PanelClientActivity activityPanelClientActivity = (PanelClientActivity) getActivity();
                                                                 activityPanelClientActivity.setHomeFragment(homeFragment);
                                                                 IntentIntegrator integrator = new IntentIntegrator(getActivity());
                                                                 integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                                                                 integrator.setPrompt("Aponte para o QR Code");
                                                                 integrator.setCameraId(0); // C??MERA TRASEIRA. SE FOR 1 ?? C??MERA DA FRENTE
                                                                 integrator.initiateScan();
                                                             }
                                                         }
        );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        YLog.d("HomeFragment", "onActivityResult", "Retorno da leitura de QR Code");
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                alert(result.getContents());
                YLog.d("HomeFragment", "onActivityResult", "Resultado do QR Code:" + result.getContents());
            } else {
                alert("Scan Cancelado");
                YLog.d("HomeFragment", "onActivityResult", "Scan Cancelado.");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void alert(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onQRCodeRead(String contents) throws NoSuchAlgorithmException {
//        YLog.d("HomeFragment", "onQRCodeRead", "QR Code lido: " + contents);
        PanelClientActivity panelClientActivity = (PanelClientActivity) getActivity();

        String otac = getString(R.string.BKE_OTAC);
        System.out.println("QR Code read: " + contents);
//        AES AES = new AES(); // Inicializando o TEA com o OTAC do cliente
        String output = AES.decrypt(contents, otac); // Decriptando o conte??do recebido do QR Code e passo para vari??vel output
        System.out.println("M1 Output: " + output);
        String[] technicianParameters = output.split("="); // Criando um array para separar as informa????es com um split

        String technicianUsername = technicianParameters[0]; // id do t??cnico na primeira posi????o
        String ISPId = technicianParameters[1]; // id da ISP na segunda posi????o
        String nonceTechnician = technicianParameters[2]; // nonce do t??cnico na terceira posi????o
        String HMACTechnician = technicianParameters[3]; // HMAC do t??cnico na quarta posi????o (o HMAC ser?? utilizado para verificar a consist??ncia da mensagem)

        presenter.setTechnicianUsername(technicianUsername);

        String contentRebuilt = "";
        String resultingHMAC = "";
        for (String parameter : technicianParameters) {
            if (!parameter.equals(technicianParameters[technicianParameters.length - 1])) { // enquanto n??o for o ??ltimo, concatena e bota o separador "="
                if (!parameter.equals(technicianParameters[technicianParameters.length - 2])) {
                    contentRebuilt = contentRebuilt.concat(parameter.concat("="));
                } else {
                    contentRebuilt = contentRebuilt.concat(parameter);
                }
            } else { // quando chegar no ??ltimo, computa HMAC.
                resultingHMAC = SecurityUtilities.hash256(contentRebuilt.concat(otac));
            }
        }
        if (HMACTechnician.equals(resultingHMAC)) { // verifico o HMAC do t??cnico utilizando o meu OTAC
            System.out.println("HMAC Output: " + HMACTechnician);
            System.out.println("HMAC Result: " + resultingHMAC);
            LoadingDialog loadingDialog = new LoadingDialog(panelClientActivity, "", true);
        } else {
            ErrorHMACDialog errorHMACDialog = new ErrorHMACDialog(panelClientActivity, "Os HMAC n??o coicidem.");
        }


        // idClient, idISP, nonceClient
        Random nonceClient = new Random(); // criando um nonce para o cliente
        Person person = panelClientActivity.getPerson(); // pegando o id do cliente

        AuthSolicitation authSolicitation = new AuthSolicitation(technicianUsername, ISPId, nonceTechnician, person.getUsername(),
                String.valueOf(nonceClient.nextLong()), otac); // Juntando as informa????es recebidas com a do cliente para formar a mensagem
        String encrypted = AES.encrypt(authSolicitation.getPayloadM2(), otac); // Encriptando as informa????es da mensagem
        System.out.println("M2 Input: " + authSolicitation.getPayloadM2());
        System.out.println("M2 Encrypted: " + encrypted);

        presenter.newAuthSolicitation(encrypted, otac); // Salvando no firebase as informa????es encryptadas + o otac
    }

    @Override
    public void showMessage4(String decrypted) {

    }

    @Override
    public void showConfimationDialog(Technician technician) {
        ConfirmAuthenticationDialog confirmAuthenticationDialog = new ConfirmAuthenticationDialog(getContext(), technician);
    }
}