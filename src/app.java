@RestController
@RequestMapping("/api/produits")
@Tag(name = "Produits", description = "Gestion des produits")
public class ProduitController {

    @Operation(summary = "Récupère la liste des produits")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                     content = @Content(array = @ArraySchema(schema = @Schema(implementation = Produit.class))))
    })
    @GetMapping
    public List<Produit> getAllProduits() {
        return produitService.findAll();
    }

    @Operation(summary = "Ajoute un nouveau produit")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Produit créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
        return new ResponseEntity<>(produitService.save(produit), HttpStatus.CREATED);
    }

    @Operation(summary = "Supprime un produit par son ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Produit supprimé"),
        @ApiResponse(responseCode = "404", description = "Produit non trouvé")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        produitService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}