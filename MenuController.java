package com.transporte.gestion.controlador.sistema;

import com.transporte.gestion.global.enums.AccionesEnum;
import com.transporte.gestion.global.modelos.MenuItem;
import com.transporte.gestion.global.modelos.SesionGlobal;
import com.transporte.gestion.vista.Flota.BusListPanel;
import com.transporte.gestion.vista.Flota.ChoferBusListPanel;
import com.transporte.gestion.vista.Personas.ChoferListPanel;
import com.transporte.gestion.vista.Infraestructura.CiudadListPanel;
import com.transporte.gestion.vista.Personas.ClienteListPanel;
import com.transporte.gestion.vista.Infraestructura.CooperativaListPanel;
import com.transporte.gestion.vista.Infraestructura.CopTermListPanel;
import com.transporte.gestion.vista.Ventas.FacturaListPanel;
import com.transporte.gestion.vista.Mantenimiento.HistorialMantenimientoPanel;
import com.transporte.gestion.vista.Operaciones.HorarioListPanel;
import com.transporte.gestion.vista.Flota.MarcaListPanel;
import com.transporte.gestion.vista.Ventas.MetodoPagoListPanel;
import com.transporte.gestion.vista.Flota.MisBusesListPanel;
import com.transporte.gestion.vista.Flota.ModeloListPanel;
import com.transporte.gestion.vista.Seguridad.PermisosListPanel;
import com.transporte.gestion.vista.Personas.PersonaListPanel;
import com.transporte.gestion.vista.Infraestructura.ProvinciaListPanel;
import com.transporte.gestion.vista.Reportes.ReportesPanel;
import com.transporte.gestion.vista.Seguridad.RolListPanel;
import com.transporte.gestion.vista.Operaciones.RutaHorarioListPanel;
import com.transporte.gestion.vista.Operaciones.RutaListPanel;
import com.transporte.gestion.vista.Mantenimiento.TallerListPanel;
import com.transporte.gestion.vista.Mantenimiento.TallerPendientesPanel;
import com.transporte.gestion.vista.Operaciones.TarifaListPanel;
import com.transporte.gestion.vista.Infraestructura.TerminalListPanel;
import com.transporte.gestion.vista.Mantenimiento.TipoMantenimientoListPanel;
import com.transporte.gestion.vista.Ventas.TipoPasajeroListPanel;
import com.transporte.gestion.vista.Seguridad.UsuariosListPanel;
import com.transporte.gestion.vista.Operaciones.ViajeListPanel;
import com.transporte.gestion.vista.sistema.PrincipalFrame;

import javax.swing.JTree;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.EnumMap;
import java.util.Map;

public class MenuController {

    private final PrincipalFrame principal;

    public MenuController(PrincipalFrame principal) {
        this.principal = principal;
    }

    public void construirMenu(JTree treeMenu) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");

        Map<AccionesEnum, DefaultMutableTreeNode> nodos = new EnumMap<>(AccionesEnum.class);

        for (AccionesEnum a : AccionesEnum.values()) {
            if (a.esCarpeta()) {
                DefaultMutableTreeNode n = new DefaultMutableTreeNode(new MenuItem(a.getLabel(), null));
                nodos.put(a, n);
            }
        }

        for (AccionesEnum a : AccionesEnum.values()) {
            if (!a.esCarpeta()) {
                continue;
            }

            AccionesEnum padre = a.getPadre();
            DefaultMutableTreeNode nodoActual = nodos.get(a);

            if (padre == null) {
                root.add(nodoActual);
            } else {
                DefaultMutableTreeNode nodoPadre = nodos.get(padre);
                if (nodoPadre != null) {
                    nodoPadre.add(nodoActual);
                }
            }
        }

        for (AccionesEnum a : AccionesEnum.values()) {
            if (a.esCarpeta()) {
                continue;
            }

            if (a.requierePermiso() && !SesionGlobal.tienePermiso(a.getPermiso())) {
                continue;
            }

            DefaultMutableTreeNode padreNode = nodos.get(a.getPadre());
            if (padreNode == null) {
                continue;
            }

            padreNode.add(new DefaultMutableTreeNode(new MenuItem(a.getLabel(), a.getAccion())));
        }

        limpiarNodosVacios(root);

        treeMenu.setModel(new DefaultTreeModel(root));
        treeMenu.setRootVisible(false);
        treeMenu.setShowsRootHandles(true);
        treeMenu.setRowHeight(26);
        treeMenu.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
    }

    public void enlazar(JTree treeMenu) {
        treeMenu.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node
                    = (DefaultMutableTreeNode) treeMenu.getLastSelectedPathComponent();
            if (node == null) {
                return;
            }

            Object obj = node.getUserObject();
            if (!(obj instanceof MenuItem)) {
                return;
            }

            MenuItem item = (MenuItem) obj;

            if (item.esCarpeta()) {
                return;
            }

            ejecutarAccion(item.getAccion());
        });
    }

    private void ejecutarAccion(String accion) {

        switch (accion) {
            case "persona.crud":
                principal.mostrarPanel(new PersonaListPanel());
                break;
            case "permisos.crud":
                principal.mostrarPanel(new PermisosListPanel());
                break;
            case "usuarios.crud":
                principal.mostrarPanel(new UsuariosListPanel());
                break;
            case "rol.crud":
                principal.mostrarPanel(new RolListPanel());
                break;
            case "marca.crud":
                principal.mostrarPanel(new MarcaListPanel());
                break;
            case "provincia.crud":
                principal.mostrarPanel(new ProvinciaListPanel());
                break;
            case "taller.crud":
                principal.mostrarPanel(new TallerListPanel());
                break;
            case "tipo_mantenimiento.crud":
                principal.mostrarPanel(new TipoMantenimientoListPanel());
                break;
            case "tipo_pasajero.crud":
                principal.mostrarPanel(new TipoPasajeroListPanel());
                break;
            case "metodo_pago.crud":
                principal.mostrarPanel(new MetodoPagoListPanel());
                break;
            case "cooperativa.crud":
                principal.mostrarPanel(new CooperativaListPanel());
                break;
            case "horario.crud":
                principal.mostrarPanel(new HorarioListPanel());
                break;
            case "modelo.crud":
                principal.mostrarPanel(new ModeloListPanel());
                break;
            case "ciudad.crud":
                principal.mostrarPanel(new CiudadListPanel());
                break;
            case "terminal.crud":
                principal.mostrarPanel(new TerminalListPanel());
                break;
            case "cliente.crud":
                principal.mostrarPanel(new ClienteListPanel());
                break;
            case "chofer.crud":
                principal.mostrarPanel(new ChoferListPanel());
                break;
            case "bus.crud":
                principal.mostrarPanel(new BusListPanel());
                break;
            case "choferBus.crud":
                principal.mostrarPanel(new ChoferBusListPanel());
                break;
            case "copterm.crud":
                principal.mostrarPanel(new CopTermListPanel());
                break;
            case "ruta.crud":
                principal.mostrarPanel(new RutaListPanel());
                break;
            case "rutahorario.crud":
                principal.mostrarPanel(new RutaHorarioListPanel());
                break;
            case "tarifa.crud":
                principal.mostrarPanel(new TarifaListPanel());
                break;
            case "viaje.crud":
                principal.mostrarPanel(new ViajeListPanel());
                break;
            case "factura.crud":
                principal.mostrarPanel(new FacturaListPanel());
                break;
            case "mantenimiento.chofer":
                principal.mostrarPanel(new MisBusesListPanel());
                break;

            case "mantenimiento.taller":
                principal.mostrarPanel(new TallerPendientesPanel());
                break;
            case "mantenimiento.historial":
                principal.mostrarPanel(new HistorialMantenimientoPanel());
                break;
            case "reporte.crud":
                principal.mostrarPanel(new ReportesPanel());
                break;
            default:
                JOptionPane.showMessageDialog(
                        principal,
                        "Acción: " + accion,
                        "Menu",
                        JOptionPane.INFORMATION_MESSAGE
                );
        }
    }

    private void limpiarNodosVacios(DefaultMutableTreeNode node) {
        for (int i = node.getChildCount() - 1; i >= 0; i--) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);

            // primero limpiar hijos
            limpiarNodosVacios(child);

            Object obj = child.getUserObject();
            boolean esCarpeta = (obj instanceof MenuItem) && ((MenuItem) obj).esCarpeta();

            // si es carpeta y quedó vacía => eliminar
            if (esCarpeta && child.getChildCount() == 0) {
                node.remove(i);
            }
        }
    }
}
