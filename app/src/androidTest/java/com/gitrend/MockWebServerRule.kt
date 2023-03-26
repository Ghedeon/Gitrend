@file:Suppress("MemberVisibilityCanBePrivate")

package com.gitrend

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.tls.HandshakeCertificates
import okhttp3.tls.HeldCertificate
import org.junit.rules.ExternalResource

internal class MockWebServerRule : ExternalResource() {
    val server = MockWebServer()

    override fun before() {
        // enable ssl by providing self-signed certificate
        val localhostCertificate = HeldCertificate.decode("instrumentation_cert.pem".readText())

        val serverCertificates = HandshakeCertificates.Builder()
            .heldCertificate(localhostCertificate)
            .build()

        server.useHttps(serverCertificates.sslSocketFactory(), tunnelProxy = false)

        server.start(8080)
    }

    override fun after() {
        server.shutdown()
    }

    fun enqueue(fileName: String, code: Int) {
        server.enqueue(
            MockResponse()
                .setResponseCode(code)
                .setBody("api/$fileName".readText())
        )
    }
}

private fun String.readText(): String = object {}.javaClass.getResource("/$this")!!.readText()