package fr.uranium;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class addToLauncherProfile {

    public static void main() {
        // Chemin du fichier
        String cheminFichier = minecraftFolder.pathToMinecraft() +System.getProperty("file.separator") +"launcher_profiles.json";

        // Texte à ajouter après les deux accolades
        String texteAAjouter = "\"3774e4c5b0e3e5e9ed4e620d50b89d17\" : {" +
                "\"created\" : \"2023-12-00T00:00:00.000Z\"," +
                "\"gameDir\" : \""+(minecraftFolder.pathToMinecraft()+System.getProperty("file.separator")+"uranium").replace("\\", "\\\\")+"\"," +
                "\"icon\" : \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAMAAAD04JH5AAAACXBIWXMAAC4iAAAuIgGq4t2SAAAAolBMVEUNSiYsu0oimVUr1UYsxUwpzUwnpFAjsksGNhINWzMQazZf6R9FyzRR3SoTeTQy2zhj0YMThDAgj0BEsmM3VzVYx3jA6s1MvG0VmyZj3Ipu5pbnxzVoZjAdyCCs1rkWPCAZtB/R+d3NsjQLNhwaPSBL0niNgDKEr5GZw6X63DSrmjQGLxsIMhsGJxwv7SM4aUFumHoHOR8OOyRSfVyH+axHcExeeV3eAAAANnRSTlP///////////////////////////3//////////v//xP///5vr////////OXQj//j/Vg///wBGjtXjAAAVMklEQVR42ryZC3ebuhKFBXFBkEQJWLFpzctAAINdsLn+/3/t7pHArzRxz7l3RVkrSYnNfOwZ7Rm57KhXu22P37d2u9OvTIdPZC+33xZ/K2WyvQTYsmqxrtn+m+LvWbyou+RwAsCF9WKxqIrdt8RvebVYrNe9JgDAoajXBFAPyXfEPxR9vCCCIR8BtgP+uYh72bH2WwpgGGoVUSkOgKSK43UtueT8O3KwZZyzfrGI42E7Agx1vOh4HVf840bYte02T5IwSfL9v8A7/AGA9/WAMqhrlQMA7JCUGkxxXLEbgl0uoUxZNk2TZU2YbP8Zwy4vkvaDABVKQHZxPUwpoLKs2RAvsDlkfvXiAlJVjMtQLXCE+T9A2BbVopLX78hlHJP6Mu55e/KBthgkRywkZtqeyp0AVdd4NctWm9UmK8Owae751WHX7vf7dnfY4e0xktsX53ccko52wKLnwzDazuiEeZ5DGmzGeJjcABfWcV3XVYXUyKZsQJGFyEfyhQiHLYSiFRZFFdcV+OmhxnfsioHio9iKU27YRXLUH0FM1QZRFusYjwAAJK1DYKRgtWmKsPmCYBuWKllliCRXiE8E60qL0KLYEAOeuz0crnuBfuSuxu7EnyXLt6wmOUgBkmDd83K1yrIS32VY5p+7bFFmUGojebwGOSmAmOtFl7d5wju6f3xdaBcA2I/4e4ViqNRLNQBuEveMM8TNNk0YZquw+LxptOFmU5bZquR8GCWIle/1nNU1w20Bc2W4lwCHpEd51NCgV948KSA565ikxDYZ0tB8LsAxzzYrqpeQ4U19XZ8AcFM8XifjxZAcPgNAkXToSxWv1qMApECHp4dLl7QNgbDalF8YdrLZZCtsmZB1ckRQAPTkUi7Wg+xvWh67bVTrQfXGUYFq4NxxpCNZkzWyCEsWZrLd7Q6fZaDRrlUSgISDVPEIQN0Hdx74Df41wLHoO6mCr/WOYMwZF/3G8I3RcraXzaVIsAp8JQV8nha9hAiAoAxO3Q8dmFf9bce9Ach5t16vtQLrGOozL3r5nd4u96IMcjc4L8/zPSwhHKfrkDjKQ7WYAJDdW7O/BcBGWOsCpP3IJQBSEf22zItlmdEVgBfZL79nlo1lGJER0XoJGJMd3s5IhIWuKdy55vsvAXR8/WJsHNzAcVPhBr/Nq3UGOGwTKVIhghfbN00fXwrRMlLE5syw8RBoZ/F4z/U6vt3D7Ca+qldSjOTnjuk6qSfc9OUaQIwA26TZDG7qea74bfiTQlh2quLPl7aL5+C8HgGQ16L9FCAfdL3Qy4DNuDANj6W+EG50lYQXoUppl5SbTcNE6oNRPBvmmcCOHOYs56ZlWD5lkveaAAFuCNh1fOWbSD/HfMR8vN9jgUUAp+D+CaBNsk0pQ6kAhJNOBJYGcGdzUFu46JKYYyEgQH3lBBfNSFL3pGZBxSq5g1DE7yEHIjI/KNDKVSZlRilA5SNNT0/GpQLeG36a5hzFIeh+J3fH+H34A0DLyPcUQEXELj2tbZqoQk94HwF2BQy/JHsCgK8Bnp5OEkSuu5yrvNn4TmngymDI3ONLM2AnF67iqXkSr+v7FqQzLZeKwL8CsCKRHJJVWaA9ZhnVgAYwtAYWEURCA1gmQyr9iUB3l3jIPwAkPaYHal6LMb5p+GRplmBpIIIPAEnWFNlmg/bUuRcAT/akgOcQAOLjJr7le3AEECxUd6trub8B2HY0eFDr1fFhK5YgU3V8FqQinV0C2JFXyAbpx4iEOnDT4AzwZGmCKGDzuaXic+7PRoJODzh41FMZjCMZq/SqdXzb9pECRcAcGE1qmNY1wD4JER8zGu2CS4AnrcBMAYzxDd+yLU9VYjzGOZ3C2LgDR4CKvFOAFwDmSIAqjAy66cRgpx47HCV1XdlsOjcCgOcGI4A9WaG1tHT8JVIEBI86w1CPgaYksPG82qtFI7hkri8IAASukiD1I1vF11+mAYDdMYH6JWYkJqIg8AUBjBKMVmgtHR3fpJsJYQhFUKlAPbseSpNBXe449VB+IpgL6qxBEKHRWCODac4CAshLmWUyXHUKQCmgCWzCtCOGtJ/je5CVqf7cK4Iuvy7CVkkwIL6DJsYFEUDJ+dJzXFgRAKyzCABwWhjXCgiYP70JwFbxn7UEsEIXAP77KT6XakgZI+1utmEucZmGL9l1LC/Q1iGjbcze0Y6c9IV6rdYAEkQKoMX4jTJYOSKiIhAEcJKAnMhxHcS3dAIsqygA4FBvRKTz8YadTu1Dj78hPOIfW4eiG7Pl7P09cJhQANZEoAEORYPwqwwtO8AVDaAkwJahmQHaqfimis/aliSgUXHou+JPVtzp+JI2yN4zDDy/IkA7eLDHpQE8z4GCyYpWFg4MZhgIXwGMXkBW6Lj+21wNCcK06SC253R7JOFyMLxoRkgAFHJ0rwLBSQMWjACjBgBgOP+hGSqAVeNiqwr/YUoBSQBKPL/qBnBy29nr054aFPnlxwAX7XiPA2KeTGe2LREsZ7PZ+5wFP2w7PTOQAjh9YROuMIOHzWaDQg08BaB3onaiNwUAj7DcMWLCCqwk/3wmvDzm+TPbJgUsls5o3jsRkAKowAJeCAJIgKnEDTAaGqccwIkCtnwjAM8zz0M0BvrD4auh9GpCDohghhpIVfB0qgMC4GVWSjouZqsGp5VOAUwSPBvKCuc0klD8L45SXwEcc4sIABCpwMYkQuShwHiTyYwAkAXYoRsIBfA05QBWaALgXvwvAY4dEfwQjgawjXT8gQEsEJzOaZkiaPgIAMQpB2SF74bvme7XH/59CQACI7BdjKSTBMYJwIazlQqgLLjnTgqMVWiQFcIFMNUkh/8BAAS274gXa5RAIcxwUnAC9DqOIoAdF9y3cUErMCIoAGFYtrgT/x7ArjAt5j0aJwLU4w8NMH+f82aF3cAxe+OCe6kAcvDgurZh3v34l937pK3AYP74rAkMFSEaAd5+LplE/PmbFSgA40IC23oQjmE7dz9UuwdwbAuWPj4/ngnsh0Ag5QB4/fXmML58xeglRoCTBADwHFvc/+z3LsBx60SPz8+PT9ZUBmeA1/94/O0n7CYQtwoY1o+Aib/4D4D7AHvnCQAgmCR48GEDKEIo4LPXn2/LUQGyK2Nywxl58d/8B8R9gNx5VksTIMTLBPDTY6+/XicAJz0pQADYLH/14ftfALjPI8GzekT7hYzQspavgv389XoBMLtQAL05cv4/CiTi8b+sm4lyqkoQhgchyEzYkWJRSkrFKjdOaXne/9Xu3z2AmGCWe++Y5CR1Iv3N3z09PUtQZmkGmpTmJiZDRyWZgv3iA8BDAgPLw/P/AxANAO8eucH0AYDkB/3JPgOoXgGWwHItAlD/C8Btl3tcag5u0ACODIuwA0DxDACqGnoFCCBQ2/8D4CIIwBu84LkVAwiMQtgngCxyegCtgGEZiMJuG+M/JyJRe7raHkRQAJAJhiEDZCmvYhnA6BVAOYlYEdv/nIhuF+Qh03UfTmAAIW0CYAHSGS9iOwXmnXmXAOTm+wOOLwAu5+0aa09RIaSeNABAPACEM701mUtUpVy0dBIYZi42h83u+i8BsPw9Yvl72MvA6gg6F7hjgN6+jBSVxQ8PWG5Qix3qxdX53wCcaANss1/RUjWgp72RAppBA8wZoLBh3HFULiKly+IBwQDAcbXbHPbn3wOcqdKlvWkUfn7A/bEGJ7jK1wCIwsTh/aR53AEM+uPlVmJ1OGL9tLr+FmCLZfdigYoPhb+IeoC3zg0AkLFNPujtZ7SZpvxnBay3SuwpCPaHr6qSKYAtCh287bDcH497kTMAS/CmFUBJGGM6RiHA9n3Mx9EAMAhgWIH8s0LJiPp5+ysA2N+tjnjncUMAtUkPIwJIgBEZeJEGSDv7YWbnGuDRf34LADZ4xHG3XJ1+AXDeL9H9457eusGap37Ds7QI5ATT9VCAxVyUsv5ckEQYBapTwBoUUH+wZMA4WC3XPwe4LJYLkh/i8TAQ1ZtWYEB4rwlgPnvYx/oPCijPtVAzj4ZBJI6HDZ6127yW4DMA6twDHfss6aB0eRRV1x+KAnjhzQ1aAuD8C/shbYbFSiAw3t8xXUIt7QACkHjWYUnnaNufu+C0PuI9qPfxz3G1kJU1CNBJ0GIxHLF9GWr7viQA752a5wVvFmMEuTjsaTdvs3udjSaC8EJnf3DDhs5AGcB6OOENGrS5kDr/pSnZR0mq5ADQU7gWMtGKCJCKfhOEf28rChuYX1Ikdi4YJKCx0EreAOT1N/cf8+MzgG4AWC3hx/UvE9Eai77VgmKQziNIgYcAFAVWWwkgiIR2g2O2r8QTQPedKxDGWDz9NhNujxs9EGgvGqNgJEAXB+QEMTNsbV9GvuP4vnA/KuCJ/WG136x/OxecV7wRjTSAhEQATwogDJhAqDjPB/svAb4RYBLgRNvAGDybIx2V1qb13AjBbCsJBB9JUfqRI5VQ6jPAu1zREPj1dHyhRLCkmWBxPGIueDbOuQAatLnSp6h8mCqj2ns4v29IhYf99ff1gN4BPO4pH2E2HJnnT/aCZbYt7U8qBR3yzrr3AcFfLJe7r2++TE/HmyVvwG1oFKhgbJ2McxTonNi2Hj71yPc0wbjlkjLKl7dvJgHOSIK8/bU8wMcBm+07PyAgEMBhuoHrBWjD+m0MkQvUVHTu/7o4FdOn8N0G3J42eeug63pn3ursQwLTxIcZUKEWPMx7o0wkxObIVw9W59vPAW60+UMEq/ieirzlPj/0HyQAAyG4gWkCIOgkGGlQi9RfYFLHzLI/rk8/rwm3egcQ9u+hVG1vsgN5NFOLEABhRPDQwBPJXYn9Xp/uTCaEF0Up7wAuontZlr6ogt5816zePH2yG8YIIw08MSvvvrOn0gbZdYpgGuCEEXgU/r0sytJ22AdjyyMFSAIWwfQ0QTCORE/EBQgWiKYjHTFN1CXTADfMhA76XwAgFPDBq2bSixkoEoJgcESHIP0iZAK6grTZLX4K8He9d+J7GRZEoET1DQEhePTRqdAOblAKqxcQCAyp5WbqCtYLgDPsY+0L+0U5wzgwXxnvCPrWa6AhkAplyASxg0CcXCJNA1zWM7IfsgQZkmGrrT15v7c+EJACnJO0F1qoEEleQRb3mbPYrC4/BbjsbG2fAIpCQQIMdbIzmOyGQK+AdgNJYHJe1BAtUmEWag1AsP7xbLhLOvtaAl9EdcuPf5t8jXzgaQSvI0AmyuhJFImJc/5pUTqyT1FgU8lbtb2NsQ7m8DEwBPTSEgQtAJJCywiCxelnZflgP8WL7QuVy6A1P7en3vcETdA1L3ivRFJq+yBI1ekHAKddNvSfFuA21xtYjU0SDBzmSIFAM8APbYBUiJHM7X5vou/zwGWX9vZT2A9jLneEHwlZtQHp+3Uj+y1eZJ7cIOKSu68RGv/2HcDZL8vBAWkY0+m5mNE+pJR122ptX2BQfRIEVVXXVRXQDyCRfsnZTAOU+fU7gF2S9QCwH7H9/B4LrH+hQ9UjdBgPFNgLqppq5K5QxGIhx6/77AOtwb1orD/fAFxVaqdlp0BnH3NyRmGo8NSo4n6ZyAttRxFwZ4M60tf9nppfRcKZDRo0TfEpCj7epsNqJyn0Dqi2T3MC56JIKkJQecXqjlpAx+kfGh3eo16F54Rj92GQNoWx/RLgEtFqkySg+GP7NCc3hS1UrbhpccnN3Gq6vTG2KdWo5T5JqAlIAJLg9hXA1sByO7EhQRd/jiT7aJAgH57b2Xt0dqr5PkA5IISTdB5omtI+fwFw84uUADJMxGRfOrKk/gM8obsseKiveCWgxl/8Z8P0W7rVSmR0CwXrWLJvkQLp4guA87ykzffEDkvdf4F6hIVrLF+oKvanmqI1Wtdi+uBXHEd2TkWV5EhO7mGSUQvV6/uEf/80hQZIbe6/KMo0ZfuGwQS5H8URPT+iVwSLUcRZChi0UKS7Lz5fQqIGAZLiXgomsOnBYAjt7UuAE5JGQ7+Xdf1HLZBlqbZPc4Kq6+Hp6CENu0iJaG7rO7cqz2uEZz2v+YJrDQHSDO7kMHCYAI8LxeXljcqkB1AO9T8k+wBoLMMoMv+0ljKnsM9zn5QQMo5JfttG1+35DFmb4lNFEYxjfFRSYlBn6T2kZO74GiAr4vMLgItAwmg4CjlwwjsfhzTov9Fk0YXOMNFLPJquGeNbuiwTRfF8Hkdzd07/YkD4u+1acLqIRIy3I5/cU75prF2Qhen4PuMY4Bwj+BsEQUY3uJSddhvy0L9JdQY7benRuZzHdNdx7hp0X8Y14thwDfxo2XOX+ne7bqnTckYPoB4lPnwQZQyQFuo6CXDbpSGNuAwCOCoLM7o3laRhhv4/5tHTdkGPhiVYdq25Tden8K3n2vP3eL3u/4TrRNcYZ0maESKekmI4avtZkawnAa5+iekfQQABbOLOMroRaNtZ0UQj5hv92c0Oms9dOsUzDNNzfeV5c89dP501pekspoMNyu5ASASigAjSQp6mANZZgf9rmtRW6Da/DV/jWdpk8adJ9Op7BgPQBv67vb3GnlEvnsL7KuIsm8U29YMsh2mk7aflbDsBcJEY8xhyyIVhmnHEJkkcJ2ESTxyC3xaeaxCA56oF3YxbeP7HLQAEzCwFAiuQ0BGP9gECYxSG4nFlhgAoCtk+vfBeyLGe3ONZeO+GAQjvz5UvBp2n/vrmtBWEMOOnkWlQsATR+e/ny2y6BGk4GfbmZ3L9Yotp67rrtQsVer9P74AAgRwx0w5lArKSfr7M9k/xVtPbIAxDKQem0hFBU8SSC5MSThghIuX//7XZIaFsEhCmqfOZ4kfkj5c+Gyp3AHUnZwAZ/mbbPd2dlJ0+MPaP9OFB6QDhiUA+G8Iy1ksUEF0LVwm8+wPds6+K9yU+Abb1R4JAIpNDQI5WmRgmKnNMgbLhJZUCjs/eI2TXYSyKkKCwJxQbeKTU5RzPbjq8qkj2Y6JScUH+KQ/wBMh9xE6ZIQB9oDK7/wgbGLPOGR4x8bPLt51TbANOhnelX3Spjltpw1y8Vcv035FWPyEEITwCKcINIfFtgEKPWoeo3xgYG2dtcRvDGlkE5mm810iMHAqZrueKqQ3Q95eCszzavQPwiH/ayUGM09wJfmjTrgBgG8D4E4LnGs68UF0+lT1nSCqQ8CE9Cw0hmW8DNXKRbCftN4JAKWPPGpXHUmKRUwuA/kpjAKfd/9oGLI8Y9EnYO7YqbdKkfenuudIVZ+ABGJ3HDHv8rRnQV+0BwOvdewj9DGAY7D8ZBcEXNeUNeqSXeWYAAAAASUVORK5CYII=\"," +
                "\"javaArgs\" : \"-Xmx6G -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -XX:G1NewSizePercent=60 -XX:G1ReservePercent=20 -XX:MaxGCPauseMillis=50 -XX:G1HeapRegionSize=32M\"," +
                "\"lastUsed\" : \"2023-12-00T00:00:00.000Z\"," +
                "\"lastVersionId\" : \"1.20.1-forge-47.2.17\"," +
                "\"name\" : \"Uranium\"," +
                "\"type\" : \"custom\"" +
                "},";
        try {
            // Lecture du fichier
            File fichier = new File(cheminFichier);
            BufferedReader lecteur = new BufferedReader(new FileReader(fichier));

            // Construction du nouveau contenu avec le texte ajouté
            StringBuilder nouveauContenu = new StringBuilder();
            String ligne;
            while ((ligne = lecteur.readLine()) != null) {
                nouveauContenu.append(ligne).append("\n");
                if (ligne.contains("  \"profiles\" : {")) {
                    nouveauContenu.append(texteAAjouter).append("\n");
                }
            }
            lecteur.close();

            // Écriture du nouveau contenu dans le fichier
            BufferedWriter ecrivain = new BufferedWriter(new FileWriter(fichier));
            ecrivain.write(nouveauContenu.toString());
            ecrivain.close();

            System.out.println("Le texte a été ajouté après les deux accolades avec succès.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
